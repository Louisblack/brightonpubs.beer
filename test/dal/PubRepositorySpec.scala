package dal

import org.scalatestplus.play.{PlaySpec, _}
import play.api.test.Helpers.{GET => GET_REQUEST}

import scala.concurrent.ExecutionContext

class PubRepositorySpec extends PlaySpec with OneAppPerSuite {

  val userId = 1
  
  private val pubRepository = app.injector.instanceOf[PubRepository]
  implicit val ec = app.injector.instanceOf[ExecutionContext]

  "Pub Repository" must {
    "list pubs in order" in {
      val eventualPubs = pubRepository.listPubs()
      eventualPubs.map {pubs =>
        pubs.size must not be 0
        pubs.head.pub.name mustEqual "112 Church St"
      }
    }

    "list pubs with visits" in {
      val eventualPubs = pubRepository.listPubsWithVisits(userId)
      eventualPubs.map {pubs =>
        pubs.size must not be 0
        val firstPub = pubs.head.pubWithLocation.pub
        firstPub.name mustEqual "112 Church St"
        pubs.head.visit mustEqual None
      }
    }

    "mark pub as visited" in {
      for {
        eventualPubs <- pubRepository.listPubsWithVisits(userId)
        _ <- pubRepository.visit(eventualPubs.head.pubWithLocation.pub.id, userId)
        pubs <- pubRepository.listPubsWithVisits(userId)
      } yield {
        pubs.head.visit must not be None
      }
    }

    "mark pub as not visited" in {
      for {
        eventualPubs <- pubRepository.listPubsWithVisits(userId)
        _ <- pubRepository.visit(eventualPubs.head.pubWithLocation.pub.id, userId)
        _ <- pubRepository.unVisit(eventualPubs.head.pubWithLocation.pub.id, userId)
        pubs <- pubRepository.listPubsWithVisits(userId)
      } yield {
        pubs.head.visit mustEqual None
      }
    }

  }

}
