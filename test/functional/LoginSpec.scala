package functional

import org.scalatestplus.play.{OneBrowserPerTest, OneServerPerTest, PlaySpec}

/**
  * Created by louis on 11/06/2017.
  */
class LoginSpec {

}
import org.scalatestplus.play._
import play.api.test.Helpers.{GET => GET_REQUEST}

class ExampleSpec extends PlaySpec with OneServerPerTest with OneBrowserPerTest with ChromeFactory {

  "Login page" must {
    "should allow login" in {
      go to (s"http://localhost:$port/login")
      pageTitle mustBe "Login"
      click on find(name("email")).value
      enter("louis@example.com")
      click on find(name("password")).value
      enter("password123")
      submit
      eventually { pageTitle mustBe "Brighton Pubs" }
    }
  }
}




