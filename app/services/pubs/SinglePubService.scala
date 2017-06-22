package services.pubs

import com.google.inject.Inject
import dal.PubRepository
import models.User

import scala.concurrent.{ExecutionContext, Future}


class SinglePubService @Inject()(pubRepository: PubRepository)(implicit ec: ExecutionContext) {

  def getPubDetail(pubId: Long, user: Option[User]): Future[Option[Pub]] = {
    user match {
      case None => pubRepository.getPub(pubId).map(_.map(Pub.apply))
      case Some(user) => pubRepository.getPubWithVisit(pubId, user.id).map(_.map(Pub.apply))
    }
  }
}
