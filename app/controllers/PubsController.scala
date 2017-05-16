package controllers

import com.google.inject.Inject
import dal.PubRepository
import play.api.libs.json.Json
import play.api.mvc._
import services.pubs.PubListService
import services.auth.{MaybeUserAuthAction, MaybeUserAuthRequest, UserAuthAction}

import scala.concurrent.ExecutionContext



class PubsController @Inject()(pubRepository: PubRepository,
                               pubListService: PubListService,
                               userAuthAction: UserAuthAction,
                               maybeUserAuthAction: MaybeUserAuthAction)(implicit ec: ExecutionContext) extends Controller {

  def index = maybeUserAuthAction.async { maybeUserAuthRequest =>
    for {
      pubs <- pubListService.listPubs(maybeUserAuthRequest.user)
    } yield {
      Ok(Json.toJson(pubs))
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
