package models

import play.api.libs.json.Json

/**
  * Created by louis on 01/05/2017.
  */
case class Visit(pubId: Long, userId: Long)

object Visit {
  implicit val writes = Json.writes[Visit]
}
