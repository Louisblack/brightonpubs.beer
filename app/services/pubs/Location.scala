package services.pubs

import play.api.libs.json.Json

case class Location(lat: Double, lng: Double)

object Location {
  implicit val writes = Json.writes[Location]

  def apply(location: models.Location): Location = {
    Location(location.lat, location.lng)
  }
}
