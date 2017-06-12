/**
  * Created by araki on 2017/01/30.
  */

import javax.inject.{Inject, Singleton}

import controllers.TLSFilter
import play.api.http.HttpFilters
import play.api.mvc.EssentialFilter
import play.filters.csrf.CSRFFilter
import play.filters.headers.SecurityHeadersFilter

@Singleton
class Filters @Inject()(csrfFilter: CSRFFilter, securityHeadersFilter: SecurityHeadersFilter, tlsFilter: TLSFilter) extends HttpFilters {
  override def filters: Seq[EssentialFilter] = Seq(tlsFilter, securityHeadersFilter)
}
