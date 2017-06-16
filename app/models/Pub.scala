package models

import play.api.libs.json.Json

case class Pub(id: Long, name: String, imgUrl: Option[String])

object Pub {
  implicit val writes = Json.writes[Pub]
}

object Status extends Enumeration {
  val open, closed = Value
}
