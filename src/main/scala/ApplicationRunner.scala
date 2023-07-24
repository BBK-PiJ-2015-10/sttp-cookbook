import movies.model
import movies.model.Movie
import movies.services.{MovieClient, MovieClientImpl}
import sttp.client3.{HttpURLConnectionBackend, Identity, SttpBackend, UriContext}
import sttp.model.Uri

object ApplicationRunner extends App{

  val urlDomain = "localhost"
  val port = 9000

  val backendConnection: SttpBackend[Identity, Any] = HttpURLConnectionBackend()

  val movieClient : MovieClient = MovieClientImpl(urlDomain,port,backendConnection)

  ///movieClient.getMovies().foreach(cat => println(cat))

  val movie = Movie("Alexander",2010,9.0)

  val cat: Either[model.Error, Movie] = movieClient.createMovie(movie);
  cat match {
    case Left(error) => println("fucker")
    case Right(movie) => println("wiid")
  }



}
