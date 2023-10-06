package performance.simulation

import io.gatling.core.Predef.Simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import performance.baseTools.UserList

class UserListDriver extends Simulation {
  private val httpProtocol = http
    .inferHtmlResources(AllowList(), DenyList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*\.svg""", """.*detectportal\.firefox\.com.*"""))
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")

  setUp(UserList.listUserScn.inject(atOnceUsers(1))).protocols(httpProtocol)

}
