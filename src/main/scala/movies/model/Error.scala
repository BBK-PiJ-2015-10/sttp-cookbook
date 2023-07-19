package movies.model

import play.api.libs.json._

case class Error(message: String)

object Error {

  implicit val errorReads: Reads[Error] = (
    (JsPath \ "message").read[String].map(m => Error(m)))

}

