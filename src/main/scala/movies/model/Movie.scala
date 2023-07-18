package movies.model

import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

case class Movie(title: String, year: Int, rating: Double)

object Movie {

  implicit val movieWrites = new Writes[Movie]{
    override def writes(o: Movie): JsValue = Json.obj(
      "title" -> o.title,
      "year" -> o.year,
      "rating" -> o.rating
    )
  }

  implicit val movieReads : Reads[Movie] = (
    (JsPath \ "title").read[String] and
      (JsPath \ "year" ).read[Int] and
      (JsPath \ "rating").read[Double]
  )(Movie.apply _)

}
