package services.pubs

import models.{PubWithLocation, PubWithLocationAndVisit}
import play.api.libs.json.Json

sealed trait Pub

case class SimplePub(id: Long, name: String, visited: Boolean) extends Pub

object SimplePub {

  implicit val writes = Json.writes[SimplePub]

  def apply(pubWithLocation: PubWithLocation): SimplePub = {
    SimplePub(pubWithLocation.pub.id, pubWithLocation.pub.name, false)
  }

  def apply(pubWithLocationAndVisit: PubWithLocationAndVisit): SimplePub = {
    val visited = pubWithLocationAndVisit.visit match {
      case None => false
      case Some(_) => true
    }
    SimplePub(pubWithLocationAndVisit.id, pubWithLocationAndVisit.name, visited)
  }
}
