package performance.simulation

import io.gatling.core.Predef.Simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import performance.baseTools.UserCreate

class UserCreateDriver extends Simulation {
  private val httpProtocol = http
    .inferHtmlResources(AllowList(), DenyList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*\.svg""", """.*detectportal\.firefox\.com.*"""))
    .acceptHeader("application/vnd.api+json")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("en-US,en;q=0.9")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")

    setUp(UserCreate.createUserScn.inject(atOnceUsers(1))).protocols(httpProtocol)
}