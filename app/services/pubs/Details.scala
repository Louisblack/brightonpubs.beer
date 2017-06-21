package services.pubs

import models.{PubWithLocation, PubWithLocationAndVisit}
import play.api.libs.json.Json

case class Details(id: Long, name: String, visited: Boolean, imgUrl: Option[String])

object Details {
  implicit val writes = Json.writes[Details]

  def apply(pubWithLocation: PubWithLocation): Details = {
    Details(pubWithLocation.pub.id, pubWithLocation.pub.name, false, pubWithLocation.pub.imgUrl)
  }

  def apply(pubWithLocationAndVisit: PubWithLocationAndVisit): Details = {
    val visited = pubWithLocationAndVisit.visit match {
      case None => false
      case Some(_) => true
    }
    Details(pubWithLocationAndVisit.id, pubWithLocationAndVisit.name, visited, pubWithLocationAndVisit.imgUrl)
  }
}
