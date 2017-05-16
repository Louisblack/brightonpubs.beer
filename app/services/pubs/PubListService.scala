package services.pubs

import com.google.inject.{Inject, Singleton}
import dal.PubRepository
import models.{PubWithLocation, PubWithLocationAndVisit, User}
import play.api.libs.json.Json

import scala.concurrent.{ExecutionContext, Future}

case class Pub(name: String, visited: Boolean)

object Pub {

  implicit val writes = Json.writes[Pub]

  def apply(pubWithLocation: PubWithLocation): Pub = {
    Pub(pubWithLocation.pub.name, false)
  }

  def apply(pubWithLocationAndVisit: PubWithLocationAndVisit): Pub = {
    val visited = pubWithLocationAndVisit.visit match {
      case None => false
      case Some(_) => true
    }
    Pub(pubWithLocationAndVisit.name, visited)
  }
}

@Singleton
class PubListService @Inject()(pubRepository: PubRepository)(implicit ec: ExecutionContext) {

  def listPubs(maybeUser: Option[User]): Future[Seq[Pub]] = {
    maybeUser match {
      case None => pubRepository.listPubs().map(_.map(Pub.apply(_)))
      case Some(user) => pubRepository.listPubsWithVisits(user.id).map(_.map(Pub.apply(_)))
    }
  }

}

