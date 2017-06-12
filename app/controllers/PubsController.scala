package controllers

import com.google.inject.Inject
import dal.PubRepository
import play.api.libs.json.Json
import play.api.mvc._
import services.auth.{MaybeUserAuthAction, UserAuthAction}
import services.pubs.{Pub, PubListService, PubStats, SimplePub}

import scala.concurrent.ExecutionContext

case class PubsStatsAndMaybeEmail(pubs: Seq[SimplePub], pubStats: PubStats, maybeEmail: Option[String])

object PubsStatsAndMaybeEmail {
  implicit val writes = Json.writes[PubsStatsAndMaybeEmail]
}

class PubsController @Inject()(pubRepository: PubRepository,
                               pubListService: PubListService,
                               userAuthAction: UserAuthAction,
                               maybeUserAuthAction: MaybeUserAuthAction)(implicit ec: ExecutionContext) extends Controller {

  def index = maybeUserAuthAction.async { maybeUserAuthRequest =>
    for {
      pubs <- pubListService.listPubs(maybeUserAuthRequest.user)
    } yield {
      Ok(Json.toJson(PubsStatsAndMaybeEmail(pubs, PubStats(pubs), maybeUserAuthRequest.user.map(_.email))))
    }
  }

  def visit(pubId: Long) = userAuthAction.async { userAuthRequest =>
    for {
      visit <- pubRepository.visit(pubId, userAuthRequest.user.id)
    } yield {
      Ok(Json.toJson(visit))
    }
  }

  def unVisit(pubId: Long) = userAuthAction.async { userAuthRequest =>
    for {
      _ <- pubRepository.unVisit(pubId, userAuthRequest.user.id)
    } yield {
      Ok("Done")
    }
  }
}
