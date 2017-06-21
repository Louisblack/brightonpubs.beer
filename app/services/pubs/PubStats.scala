package services.pubs

import play.api.libs.json.Json

case class PubStats(visited: Int, total: Int, percentage: Int)

object PubStats {
  implicit val writes = Json.writes[PubStats]

  def apply(pubs: Seq[Pub]) = {
    getPubStats(pubs)
  }

  private def getPubStats(pubs: Seq[Pub]): PubStats = {
    val visited = pubs.filter(_.details.visited).length
    val total = pubs.length
    val percentage = visited match {
      case 0 => 0
      case _ => (visited.toDouble / total) * 100
    }
    PubStats(visited, total, percentage.toInt)
  }
}

