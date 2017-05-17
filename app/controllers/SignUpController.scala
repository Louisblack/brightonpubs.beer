package controllers

import com.google.inject.{Inject, Singleton}
import play.api.data.Form
import play.api.data.Forms.{mapping, _}
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}
import services.auth.SignUpService

import scala.concurrent.{ExecutionContext, Future}


case class UserSignUpData(email: String, password: String, passwordAgain: String)

object UserSignUpData {
  val userSignUpForm = Form {
    mapping(
      "email" -> email,
      "password" -> nonEmptyText,
      "passwordAgain" -> nonEmptyText
    )(UserSignUpData.apply)(UserSignUpData.unapply) verifying("Failed form constraints", fields => fields match {
      case signUpData => UserSignUpData.validate(signUpData).isDefined
    })
  }

  def validate(signUpData: UserSignUpData) = {
    signUpData.password match {
      case signUpData.passwordAgain => Some(signUpData)
      case _ => None
    }
  }
}

@Singleton
class SignUpController @Inject()(val signUpService: SignUpService,
                                 val messagesApi: MessagesApi)(implicit ec: ExecutionContext)
  extends Controller with I18nSupport {

  val userSignUpForm = UserSignUpData.userSignUpForm

  def signup = Action { implicit request =>
    Ok(views.html.auth.signup(userSignUpForm, None))
  }

  def doSignUp = Action.async(parse.anyContent) { implicit request =>
    userSignUpForm.bindFromRequest.fold(
      _ => Future.successful(Ok(views.html.auth.signup(userSignUpForm, Some("Sign up Failed")))),
      signUpData => {
        for {
          maybeCookie <- signUpService.signUp(signUpData.email, signUpData.password)
        } yield {
          maybeCookie match {
            case Right(cookie) =>
              Redirect("/").withCookies(cookie)
            case Left(message) =>
              Ok(views.html.auth.signup(userSignUpForm, Some(message)))
          }
        }
      }
    )
  }

}
