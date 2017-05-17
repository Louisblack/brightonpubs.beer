package services.auth

import com.google.inject.{Inject, Singleton}
import dal.UserRepository
import models.User
import org.mindrot.jbcrypt.BCrypt
import play.api.mvc.Cookie

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SignUpService @Inject()(userRepository: UserRepository,
                              cookieCreator: CookieCreator)(implicit ec: ExecutionContext) {

  def signUp(email: String, password: String): Future[Either[String, Cookie]] = {

    val future = for {
      user <- userRepository.getUserByEmail(email)
    } yield {
      createUserAndGenerateCookie(user, email, password)
    }
    future.flatMap(identity)
  }

  private def createUserAndGenerateCookie(maybeUser: Option[User], email: String, password: String): Future[Either[String, Cookie]] = {
    maybeUser match {
      case Some(_) => Future(Left("E-mail already exists"))
      case None => createUserAndGenerateCookie(email, password)
    }
  }

  private def createUserAndGenerateCookie(email: String, password: String): Future[Either[String, Cookie]] = {
    for {
      user <- createUser(email, password)
    } yield {
      Right(cookieCreator.createCookie(user))
    }
  }

  private def createUser(email: String, password: String): Future[User] = {
    val hashedPw = BCrypt.hashpw(password, BCrypt.gensalt())
    userRepository.create(email, hashedPw)
  }

}
