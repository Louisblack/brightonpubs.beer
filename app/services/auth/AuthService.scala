package services.auth

import com.google.inject.{Inject, Singleton}
import dal.UserRepository
import models.User
import org.mindrot.jbcrypt.BCrypt
import play.api.cache.CacheApi
import play.api.mvc.{Cookie, RequestHeader}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AuthService @Inject()(userRepository: UserRepository,
                            cacheApi: CacheApi,
                            cookieCreator: CookieCreator)(implicit ec: ExecutionContext) {


  def login(userCode: String, password: String): Future[Option[Cookie]] = {
    for {
      maybeUser <- checkUser(userCode, password)
    } yield {
      maybeUser match {
        case Some(user) => Some(cookieCreator.createCookie(user))
        case None => None
      }
    }
  }

  private def checkUser(userCode: String, password: String): Future[Option[User]] = {
    for {
      maybeUser <- userRepository.getUserByEmail(userCode)
    } yield {
      maybeUser.flatMap { user =>
        if (BCrypt.checkpw(password, user.password))
          Some(user)
        else None
      }
    }
  }

  def checkCookie(header: RequestHeader): Option[User] = {
    for {
      cookie <- header.cookies.get(CookieCreator.cookieHeader)
      user <- cacheApi.get[User](cookie.value)
    } yield {
      user
    }
  }
}
