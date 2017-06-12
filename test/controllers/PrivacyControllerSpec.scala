package controllers

import org.scalatestplus.play.PlaySpec
import play.api.mvc.{Result, Results}
import play.api.test.FakeRequest
import play.api.test.Helpers._

import scala.concurrent.Future

class PrivacyControllerSpec extends PlaySpec with Results {

  "PrivacyController#privacy" must {

    "return privacy policy" in {
      val controller = new PrivacyController
      val result: Future[Result] = controller.privacy().apply(FakeRequest())
      val bodyText: String = contentAsString(result)
      bodyText must include ("Brighton Pubs Terms of Service and Privacy Policy")
    }
  }

}
