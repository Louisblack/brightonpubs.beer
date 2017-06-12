package controllers

import com.google.inject.Singleton
import play.api.mvc.{Action, Controller}


@Singleton
class PrivacyController extends Controller  {

  def privacy = Action { implicit request =>
    Ok(views.html.privacy())
  }

}
