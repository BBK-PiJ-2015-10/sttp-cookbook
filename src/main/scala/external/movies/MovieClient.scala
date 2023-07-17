package external.movies

import sttp.client3._
import sttp.model.Uri

trait MovieClient {

  def getMovies() : String

}


case class MovieClientImpl(url: String, connection: SttpBackend[Identity, Any]) extends MovieClient {
  override def getMovies(): String = {

    val response: Identity[Response[Either[String, String]]] =
      basicRequest.get(Uri(url)).send(connection)

    response.body match {
      case Left(error) => println(s"fucker : $error")
      case Right(result) => println(s"dog : $result")
    }

    "done"

  }
}
