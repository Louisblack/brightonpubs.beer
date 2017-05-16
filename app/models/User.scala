package models

import play.api.libs.json.Json

case class User(id: Long, email: String, password: String)

object User {
  implicit val writes = Json.writes[User]
}