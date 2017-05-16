package models

import play.api.libs.json.Json

case class Location(id: Long, pubId: Long, lat:Double, lng: Double)

object Location {
  implicit val writes = Json.writes[Location]
}
