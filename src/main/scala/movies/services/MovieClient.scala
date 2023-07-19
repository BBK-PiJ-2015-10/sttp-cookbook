package movies.services

import movies.model.{Error, Movie}
import play.api.libs.json.{JsError, JsResult, JsSuccess, Json}
import sttp.client3._
import sttp.model.Uri

trait MovieClient {

  def getMovies() : List[Movie]

}


case class MovieClientImpl(urlDomain: String, port: Int,connection: SttpBackend[Identity, Any]) extends MovieClient {
  override def getMovies(): List[Movie]= {

    val uri: Uri = Uri.apply(urlDomain).port(port).addPath(Seq("movies"))

    val response: Identity[Response[Either[String, String]]] =
      basicRequest.get(uri).send(connection)

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
