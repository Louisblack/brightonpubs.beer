package services.auth

import dal.UserRepository
import models.User
import org.junit.runner.RunWith
import org.specs2.concurrent.ExecutionEnv
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.specs2.specification.AfterEach
import play.api.cache.CacheApi
import play.api.mvc.Cookie

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

@RunWith(classOf[JUnitRunner])
class AuthServiceSpec(implicit ee: ExecutionEnv) extends Specification with Mockito {

  private val cookieCreator = mock[CookieCreator]
  private val cacheApi = mock[CacheApi]

  private val userRepository = mock[UserRepository]
  val authService = new AuthService(userRepository, cacheApi, cookieCreator)

  private val pw = "$2a$10$niF.amAexQMHaevqlkganeSjvMHfTq/OdISyj8/5BQy1FHvlbi3Ne"
  private val user = User(1, "louis@test.com", pw)
  private val cookie = Cookie(CookieCreator.cookieHeader, "", Some(100))

  cookieCreator.createCookie(user) returns cookie

  "AuthService#login" should {

    "return cookie if login succeeds" in {
      val userRepository = mock[UserRepository]
      val authService = new AuthService(userRepository, cacheApi, cookieCreator)
      userRepository.getUserByEmail("louis@test.com") returns Future(Some(user))

      val eventualMaybeCookie = authService.login("louis@test.com", "password123")

      Await.result(eventualMaybeCookie, Duration.Inf) must beSome(cookie)
    }

    "return None if user doesn't exist" in {
      val userRepository = mock[UserRepository]
      val authService = new AuthService(userRepository, cacheApi, cookieCreator)
      userRepository.getUserByEmail("louis@test.com") returns Future(None)

      val eventualMaybeCookie = authService.login("louis@test.com", "password123")

      Await.result(eventualMaybeCookie, Duration.Inf) must beNone
    }

    "return None if password doesn't match" in {
      val userRepository = mock[UserRepository]
      val authService = new AuthService(userRepository, cacheApi, cookieCreator)
      userRepository.getUserByEmail("louis@test.com") returns Future(Some(user))

      val eventualMaybeCookie = authService.login("louis@test.com", "wrong")

      Await.result(eventualMaybeCookie, Duration.Inf) must beNone
    }

  }

}
