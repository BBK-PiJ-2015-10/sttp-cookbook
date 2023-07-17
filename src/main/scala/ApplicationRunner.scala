import external.movies.{MovieClient, MovieClientImpl}
import sttp.client3.{HttpURLConnectionBackend, Identity, SttpBackend}

object ApplicationRunner extends App{

  val url = "http://httpbin.org/pet"

  val backendConnection: SttpBackend[Identity, Any] = HttpURLConnectionBackend()

  val movieClient : MovieClient = MovieClientImpl(url,backendConnection)

  movieClient.getMovies()

}
