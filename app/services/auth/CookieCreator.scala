package services.auth

import java.security.MessageDigest
import java.util.UUID
import java.util.concurrent.TimeUnit

import com.google.inject.{Inject, Singleton}
import models.User
import org.apache.commons.codec.binary.Base64
import play.api.cache.CacheApi
import play.api.mvc.Cookie

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.Duration

@Singleton
class CookieCreator @Inject()(cacheApi: CacheApi)(ec: ExecutionContext) {

  def createCookie(user: User): Cookie = {
    val randomPart = UUID.randomUUID.toString.toUpperCase
    val userPart = user.id.toString.toUpperCase
    val key = s"$randomPart|$userPart"
    val token = Base64.encodeBase64String(CookieCreator.mda.digest(key.getBytes))
    val duration = Duration.create(2, TimeUnit.DAYS)
    cacheApi.set(token, user, duration)
    Cookie(CookieCreator.cookieHeader, token, maxAge = Some(duration.toSeconds.toInt))
  }

}

object CookieCreator {
  val mda = MessageDigest.getInstance("SHA-512")
  val cookieHeader = "X-Auth-Token"
}
