package movies.services

import com.typesafe.scalalogging.Logger
import movies.model.{Error, Movie}
import play.api.libs.json.{JsError, JsResult, JsSuccess, Json}
import sttp.client3._
import sttp.model.Uri
import sttp.client3.playJson._

trait MovieClient {

  def getMovies() : List[Movie]

  def createMovie(movie: Movie) : Either[Error,Movie]

}


case class MovieClientImpl(urlDomain: String, port: Int,connection: SttpBackend[Identity, Any]) extends MovieClient {

  val moviesPath = Seq("movies")
  val moviesUri : Uri = Uri.apply(urlDomain).port(port).addPath(moviesPath)

  val logger = Logger(getClass.getName)

  override def createMovie(movie: Movie): Either[Error,Movie] = {

    val json = Json.toJson(movie)
    val response: Identity[Response[Either[String, String]]] = basicRequest.body(json)
      .post(moviesUri).send(connection)

    val responseObject = response.body match {
      case Left(error) =>
        //TODO: Check what happened
      println(error)
        Left(Error(error))
      case Right(result) => {
        val json = Json.parse(result)
        logger.info(s"Received response $json")
        println(json)
        val movies = json.validate[Movie]
        Right(movies.get)
      }
    }
    responseObject
  }


  override def getMovies(): List[Movie]= {

    val response: Identity[Response[Either[String, String]]] =
      basicRequest.get(moviesUri).send(connection)

    val responseObject = response.body match {
      case Left(error) =>   JsError(error)
      case Right(result) => {
        val json = Json.parse(result)
        val movies = json.validate[Seq[Movie]]
        movies
      }
    }

    if (responseObject.isSuccess){
        responseObject.get.toList
    } else {
        List()
    }

  }


}
