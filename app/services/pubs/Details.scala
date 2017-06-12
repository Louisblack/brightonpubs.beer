package services.pubs

import play.api.libs.json.Json

case class Details(location: Location)

object Details {
  implicit val writes = Json.writes[Details]

  def apply(location: models.Location): Details = {
    Details(Location(location))
  }
}
