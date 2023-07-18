package external.movies

import sttp.client3._
import sttp.model.Uri

trait MovieClient {

  def getMovies() : String

}


case class MovieClientImpl(urlDomain: String, port: Int,connection: SttpBackend[Identity, Any]) extends MovieClient {
  override def getMovies(): String = {

    val uri: Uri = Uri.apply(urlDomain).port(port).addPath(Seq("movies"))

    val response: Identity[Response[Either[String, String]]] =
      basicRequest.get(uri).send(connection)

    response.body match {
      case Left(error) => println(s"fucker : $error")
      case Right(result) => println(s"dog : $result")
    }

    "done"

  }
}
