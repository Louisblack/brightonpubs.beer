package services.pubs

import models.{PubWithLocation, PubWithLocationAndVisit}
import play.api.libs.json.Json

case class DetailedPub(pub: SimplePub, details: Details)

object DetailedPub {
  implicit val writes = Json.writes[DetailedPub]

  def apply(pubWithLocation: PubWithLocation): DetailedPub = {
    DetailedPub(SimplePub(pubWithLocation), Details(pubWithLocation.location))
  }

  def apply(pubWithLocationAndVisit: PubWithLocationAndVisit): DetailedPub = {
    DetailedPub(SimplePub(pubWithLocationAndVisit), Details(pubWithLocationAndVisit.pubWithLocation.location))
  }

}
