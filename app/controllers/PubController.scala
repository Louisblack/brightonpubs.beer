package controllers

import com.google.inject.Inject
import dal.PubRepository
import play.api.libs.json.Json
import play.api.mvc.Controller
import services.auth.{MaybeUserAuthAction, UserAuthAction}
import services.pubs.{PubListService, PubStats, SinglePubService}

import scala.concurrent.ExecutionContext

class PubController @Inject()(singlePubService: SinglePubService,
                              maybeUserAuthAction: MaybeUserAuthAction)(implicit ec: ExecutionContext) extends Controller {

  def index(pubId: Long) = maybeUserAuthAction.async {maybeUserAuthRequest =>
    for {
      pub <- singlePubService.getPubDetail(pubId, maybeUserAuthRequest.user)
    }
    yield {
      Ok(Json.toJson(pub.get))
    }
  }

}
