package models

import play.api.libs.json.Json

case class Pub(id: Long, name: String)

object Pub {
  implicit val writes = Json.writes[Pub]
}
