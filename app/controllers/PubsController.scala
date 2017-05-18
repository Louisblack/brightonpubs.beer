package controllers

import com.google.inject.Inject
import dal.PubRepository
import models.User
import play.api.libs.json.Json
import play.api.mvc._
import services.auth.{MaybeUserAuthAction, UserAuthAction}
import services.pubs.{Pub, PubListService}

import scala.concurrent.ExecutionContext

case class PubsAndMaybeEmail(pubs: Seq[Pub], maybeEmail: Option[String])

object PubsAndMaybeEmail {
  implicit val writes = Json.writes[PubsAndMaybeEmail]
}

class PubsController @Inject()(pubRepository: PubRepository,
                               pubListService: PubListService,
                               userAuthAction: UserAuthAction,
                               maybeUserAuthAction: MaybeUserAuthAction)(implicit ec: ExecutionContext) extends Controller {

  def index = maybeUserAuthAction.async { maybeUserAuthRequest =>
    for {
      pubs <- pubListService.listPubs(maybeUserAuthRequest.user)
    } yield {
      Ok(Json.toJson(PubsAndMaybeEmail(pubs, maybeUserAuthRequest.user.map(_.email))))
    }
  }

  def visit(pubId: Long) = userAuthAction.async { userAuthRequest =>
    for {
      visit <- pubRepository.visit(pubId, userAuthRequest.user.id)
    } yield {
      Ok(Json.toJson(visit))
    }
  }
}
