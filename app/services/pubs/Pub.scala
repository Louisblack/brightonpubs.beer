package services.pubs

import models.{PubWithLocation, PubWithLocationAndVisit}
import play.api.libs.json.Json

case class Pub(details: Details, location: Location)

object Pub {
  implicit val writes = Json.writes[Pub]

  def apply(pubWithLocation: PubWithLocation): Pub = {
    Pub(Details(pubWithLocation), Location(pubWithLocation.location))
  }

  def apply(pubWithLocationAndVisit: PubWithLocationAndVisit): Pub = {
    Pub(Details(pubWithLocationAndVisit), Location(pubWithLocationAndVisit.pubWithLocation.location))
  }

}
