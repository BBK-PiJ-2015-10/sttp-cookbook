import external.movies.{MovieClient, MovieClientImpl}
import sttp.client3.{HttpURLConnectionBackend, Identity, SttpBackend, UriContext}
import sttp.model.Uri

object ApplicationRunner extends App{

  val url: Uri = uri"http://httpbin.org/get"

  val backendConnection: SttpBackend[Identity, Any] = HttpURLConnectionBackend()

  val movieClient : MovieClient = MovieClientImpl(url,backendConnection)

  movieClient.getMovies()

}
