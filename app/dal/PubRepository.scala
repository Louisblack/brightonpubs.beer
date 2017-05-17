package dal

import com.google.inject.{Inject, Singleton}
import models._
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class PubRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {


  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  private class Visits(tag: Tag) extends Table[Visit](tag, "visits") {
    def pubId = column[Long]("pub_id")

    def userId = column[Long]("user_id")

    def * = (pubId, userId) <> ((Visit.apply _).tupled, Visit.unapply)
  }

  private class Locations(tag: Tag) extends Table[Location](tag, "locations") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def pubId = column[Long]("pub_id")

    def lat = column[Double]("lat")

    def lng = column[Double]("lng")

    def * = (id, pubId, lat, lng) <> ((Location.apply _).tupled, Location.unapply)
  }

  private class Pubs(tag: Tag) extends Table[Pub](tag, "pubs") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def * = (id, name) <> ((Pub.apply _).tupled, Pub.unapply)
  }

  private val visits = TableQuery[Visits]
  private val locations = TableQuery[Locations]
  private val pubs = TableQuery[Pubs]


  private def userVisits(userId: Long) = {
    visits.filter(_.userId === userId)
  }

  def listPubs(): Future[Seq[PubWithLocation]] = {
    db.run {
      val query = for {
        (pub, location) <- pubs join locations on (_.id === _.pubId)
      } yield {
        (pub, location)
      }
      query.result
    }.map(_.map(PubWithLocation.apply _))
  }

  def listPubsWithVisits(userId: Long): Future[Seq[PubWithLocationAndVisit]] = {
    db.run(pubsWithLocationsQuery(userId)).map(_.map(PubWithLocationAndVisit.apply _))
  }

  private def pubsWithLocationsQuery(userId: Long) = {
    val query = for {
      ((pub, location), visit) <- pubs join locations on (_.id === _.pubId) joinLeft userVisits(userId) on (_._1.id === _.pubId)
    } yield {
      ((pub, location), visit)
    }
    query.result
  }

  def visit(pubId: Long, userId: Long): Future[Visit] = {
    for {
      _ <- db.run(visitInsert(pubId, userId))
    } yield {
      Visit(pubId, userId)
    }
  }

  private def visitInsert(pubId: Long, userId: Long) = {
    visits += Visit(pubId, userId)
  }
}
