package models

import play.api.libs.json.Json

case class PubWithLocationAndVisit(pubWithLocation: PubWithLocation, visit: Option[Visit]) {


  def name: String = {
    pubWithLocation.pub.name
  }

  def id: Long = {
    pubWithLocation.pub.id
  }

  def imgUrl: Option[String] = {
    pubWithLocation.pub.imgUrl
  }

}

object PubWithLocationAndVisit {
  implicit val writes = Json.writes[PubWithLocationAndVisit]

  def apply(t: ((Pub, Location), Option[Visit])): PubWithLocationAndVisit = {
    PubWithLocationAndVisit(PubWithLocation(t._1._1, t._1._2), t._2)
  }
}