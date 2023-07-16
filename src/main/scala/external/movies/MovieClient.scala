package external.movies

import sttp.client3._

object MovieClient {

  val backend = HttpURLConnectionBackend()

  val response: Identity[Response[Either[String, String]]] =
    basicRequest.get(uri"http://httpbin.org/get").send(backend)

  response.body  match {
    case Left(error) =>
    case Right(result) =>
  }

}
