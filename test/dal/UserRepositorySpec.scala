package dal

import org.scalatestplus.play.{PlaySpec, _}
import play.api.test.Helpers.{GET => GET_REQUEST}

import scala.concurrent.ExecutionContext

class UserRepositorySpec extends PlaySpec with OneAppPerSuite {

  private val userRepository = app.injector.instanceOf[UserRepository]
  implicit val ec = app.injector.instanceOf[ExecutionContext]

  "User Repository" must {
    "find a user that exists" in {
      val user = userRepository.getUserByEmail("louis@example.com")
      user.map(_.get.email mustEqual "louis@example.com")
    }

    "not find a user that doesn't exist" in {
      val user = userRepository.getUserByEmail("nobody@example.com")
      user.map(_ mustEqual None)
    }

    "create a new user" in {
      val eventualUser = userRepository.create("louis@pubs.com", "password")
      eventualUser.map{ user =>
        user.id must not be  0
        user.email mustEqual "louis@pubs.com"
        user.password must not equal "password"
      }
    }
  }

}
