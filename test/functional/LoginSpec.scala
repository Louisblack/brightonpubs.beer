package functional

import org.scalatestplus.play.{OneBrowserPerTest, OneServerPerTest, PlaySpec, _}
import play.api.test.Helpers.{GET => GET_REQUEST}

class LoginSpec extends PlaySpec with OneServerPerTest with OneBrowserPerTest with ChromeFactory {

  "Login page" must {
    "should allow login for correct credentials" in {
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




