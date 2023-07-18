import external.movies.{MovieClient, MovieClientImpl}
import sttp.client3.{HttpURLConnectionBackend, Identity, SttpBackend, UriContext}
import sttp.model.Uri

object ApplicationRunner extends App{

  val urlDomain = "localhost"
  val port = 9000

  val backendConnection: SttpBackend[Identity, Any] = HttpURLConnectionBackend()

  val movieClient : MovieClient = MovieClientImpl(urlDomain,port,backendConnection)

  movieClient.getMovies()

}
