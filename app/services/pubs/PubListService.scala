package services.pubs

import com.google.inject.{Inject, Singleton}
import dal.PubRepository
import models.{PubWithLocation, PubWithLocationAndVisit, User}
import play.api.libs.json.Json

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class PubListService @Inject()(pubRepository: PubRepository)(implicit ec: ExecutionContext) {

  def listPubs(maybeUser: Option[User]): Future[Seq[SimplePub]] = {
    maybeUser match {
      case None => pubRepository.listPubs().map(_.map(SimplePub.apply(_)))
      case Some(user) => pubRepository.listPubsWithVisits(user.id).map(_.map(SimplePub.apply(_)))
    }
  }

}

