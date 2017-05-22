package controllers

import com.google.inject.{Inject, Singleton}
import play.api.data.Form
import play.api.data.Forms.{mapping, _}
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}
import services.auth.AuthService

import scala.concurrent.{ExecutionContext, Future}

case class UserLoginData(email: String, password: String)

/**
  * Created by louis on 08/05/2017.
  */
@Singleton
class AuthController @Inject()(val authService: AuthService,
                               val messagesApi: MessagesApi)(implicit ec: ExecutionContext)
  extends Controller with I18nSupport {

  val userDataForm = Form {
    mapping(
      "email" -> nonEmptyText,
      "password" -> nonEmptyText
    )(UserLoginData.apply)(UserLoginData.unapply)
  }

  def login = Action { implicit request =>
    Ok(views.html.auth.login(userDataForm, None))
  }

  def doLogin = Action.async(parse.anyContent) { implicit request =>
    userDataForm.bindFromRequest.fold(
      formWithErrors => Future.successful(Ok(views.html.auth.login(userDataForm, Some("Oops, that doesn't look right.")))),
      userData => {
        for {
          maybeCookie <- authService.login(userData.email, userData.password)
        } yield {
          maybeCookie match {
            case Some(cookie) =>
              Redirect("/").withCookies(cookie)
            case None =>
              Ok(views.html.auth.login(userDataForm, Some("Oops, email or password were incorrect.")))
          }
        }
      }
    )
  }


}
