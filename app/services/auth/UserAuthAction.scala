package services.auth

import com.google.inject.{Inject, Singleton}
import models.User
import play.api.mvc._

import scala.concurrent.Future

case class UserAuthRequest[A](user: User, request: Request[A])
  extends WrappedRequest[A](request)

@Singleton
class UserAuthAction @Inject() (authService: AuthService) extends ActionBuilder[UserAuthRequest] {

  override def invokeBlock[A](request: Request[A],
                              block: (UserAuthRequest[A]) =>
                                Future[Result]): Future[Result] = {
    val maybeUser = authService.checkCookie(request)
    maybeUser match {
      case None => Future.successful(Results.Redirect("/"))
      case Some(user) => block(UserAuthRequest(user, request))
    }
  }
}

case class MaybeUserAuthRequest[A](user: Option[User], request: Request[A])
  extends WrappedRequest[A](request)

@Singleton
class MaybeUserAuthAction @Inject() (authService: AuthService) extends ActionBuilder[MaybeUserAuthRequest] {

  override def invokeBlock[A](request: Request[A],
                              block: (MaybeUserAuthRequest[A]) =>
                                Future[Result]): Future[Result] = {
    val maybeUser = authService.checkCookie(request)
    maybeUser match {
      case None => block(MaybeUserAuthRequest(None, request))
      case Some(user) => block(MaybeUserAuthRequest(Some(user), request))
    }
  }
}
