package dal

import com.google.inject.Inject
import models.User
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class UserRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  private class Users(tag: Tag) extends Table[User](tag, "users") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def email = column[String]("email")

    def password = column[String]("password")

    def * = (id, email, password) <> ((User.apply _).tupled, User.unapply)
  }

  private val users = TableQuery[Users]

  def getUserByEmail(email: String): Future[Option[User]] = {
    db.run(byEmail(email).result.headOption)
  }

  private def byEmail(email: String) = {
    users.filter(_.email === email)
  }

  def create(email: String, password: String): Future[User] = db.run {
    (users returning users.map(_.id)
      into ((user, id) => user.copy(id = id))
      ) += User(0, email, password)
  }

}
