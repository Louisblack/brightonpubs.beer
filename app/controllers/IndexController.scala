package controllers

import com.google.inject.{Inject, Singleton}
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}
import services.auth.AuthService

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class IndexController @Inject()(val authService: AuthService,
                               val messagesApi: MessagesApi)(implicit ec: ExecutionContext)
  extends Controller with I18nSupport {


  def index = Action { implicit request =>
    Ok(views.html.index())
  }



}
