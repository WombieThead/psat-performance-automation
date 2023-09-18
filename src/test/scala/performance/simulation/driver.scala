package performance.simulation

import io.gatling.core.Predef.Simulation

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.core.structure
import io.gatling.http.Predef._
import io.gatling.core.structure.ChainBuilder
import performance.baseTools.{AdminAuthenticate, Authenticate, CompanyCreate, UserCreate}

class driver extends Simulation {
  private val httpProtocol = http
    .inferHtmlResources(AllowList(), DenyList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*\.svg""", """.*detectportal\.firefox\.com.*"""))
    .acceptHeader("application/vnd.api+json")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("en-US,en;q=0.9")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")

//  setUp(Authenticate.userLoginFullScn.inject(atOnceUsers(1))).protocols(httpProtocol)
//  setUp(Authenticate.userLoginMinimalScn.inject(atOnceUsers(1))).protocols(httpProtocol)
//  setUp(AdminAuthenticate.adminLoginFullScn.inject(atOnceUsers(1))).protocols(httpProtocol)
//  setUp(AdminAuthenticate.adminLoginMinimalScn.inject(atOnceUsers(1))).protocols(httpProtocol)
//  setUp(CompanyCreate.companyCreateScn.inject(atOnceUsers(1))).protocols(CompanyCreate.httpProtocol)
  setUp(UserCreate.createUserScn.inject(atOnceUsers(1))).protocols(httpProtocol)

}
