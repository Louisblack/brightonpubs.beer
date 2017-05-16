package models

import play.api.libs.json.Json

case class PubWithLocation(pub: Pub, location: Location)

object PubWithLocation {
  implicit val writes = Json.writes[PubWithLocation]

  def apply(t: (Pub, Location)): PubWithLocation = {
    PubWithLocation(t._1, t._2)
  }
}
