package services.pubs

import com.google.inject.Inject
import dal.PubRepository
import models.User

import scala.concurrent.{ExecutionContext, Future}


class SinglePubService @Inject()(pubRepository: PubRepository)(implicit ec: ExecutionContext) {

  def getPubDetail(pubId: Long, user: Option[User]): Future[Option[Pub]] = {
    for {
      maybePubWithLocation <- pubRepository.getPub(pubId)
    } yield {
      maybePubWithLocation.map(Pub.apply)
    }
  }


}
