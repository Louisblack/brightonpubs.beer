package controllers

import com.google.inject.Inject
import dal.PubRepository
import models.User
import play.api.libs.json.Json
import play.api.mvc.Controller
import services.auth.{MaybeUserAuthAction, UserAuthAction}
import services.pubs.{DetailedPub, PubListService, PubStats, SinglePubService}

import scala.concurrent.ExecutionContext

case class PubAndMaybeUser(detail: DetailedPub, maybeEmail: Option[String])

object PubAndMaybeUser {
  implicit val writes = Json.writes[PubAndMaybeUser]
}

class PubController @Inject()(singlePubService: SinglePubService,
                              maybeUserAuthAction: MaybeUserAuthAction)(implicit ec: ExecutionContext) extends Controller {

  def index(pubId: Long) = maybeUserAuthAction.async {maybeUserAuthRequest =>
    for {
      pub <- singlePubService.getPubDetail(pubId, maybeUserAuthRequest.user)
    }
    yield {
      if (pub.isDefined)
        Ok(Json.toJson(PubAndMaybeUser(pub.get, maybeUserAuthRequest.user.map(_.email))))
      else NotFound
    }
  }

}
