package services.auth

import dal.UserRepository
import models.User
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.specification.BeforeEach
import org.specs2.mock._
import org.specs2.mutable._
import org.specs2.concurrent.ExecutionEnv
import org.specs2.mock.mockito.MockitoMatchers
import org.specs2.runner.JUnitRunner
import play.api.mvc.Cookie

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future, duration}

@RunWith(classOf[JUnitRunner])
case class SignUpServiceSpec(implicit ee: ExecutionEnv) extends Specification with Mockito {

  private val userRepository = mock[UserRepository]
  private val cookieCreator = mock[CookieCreator]

  private val signUpService = new SignUpService(userRepository, cookieCreator)

  "SignUpService#signUp" should {

    "return error string if user exists" in {
      userRepository.getUserByEmail("louis") returns Future(Some(User(1, "louis", "test")))
      val eventualErrorOrCookie = signUpService.signUp("louis", "test")

      Await.result(eventualErrorOrCookie, Duration.Inf) must beLeft[String]("E-mail already exists")
    }

    "return cookie if user doesn't exist" in {
      val user = User(1, "louis", "test")
      val cookie = Cookie(CookieCreator.cookieHeader, "", Some(100))

      userRepository.getUserByEmail("louis") returns Future(None)
      userRepository.create(MockitoMatchers.anyString, MockitoMatchers.anyString) returns Future(user)
      cookieCreator.createCookie(user) returns cookie

      val eventualErrorOrCookie = signUpService.signUp("louis", "test")

      Await.result(eventualErrorOrCookie, Duration.Inf) must beRight[Cookie](cookie)
    }



  }

}
