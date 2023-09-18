package performance.baseTools

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import io.gatling.core.structure.ChainBuilder

object AdminAuthenticate {

  private val httpProtocol = http
//    .baseUrl("https://admin.stagbot.io")
    .inferHtmlResources(AllowList(), DenyList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*\.svg""", """.*detectportal\.firefox\.com.*"""))
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
  
  private val headers_0 = Map(
  		"accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
  		"accept-encoding" -> "gzip, deflate, br",
  		"accept-language" -> "en-US,en;q=0.9",
  		"sec-ch-ua" -> """Not/A)Brand";v="99", "Google Chrome";v="115", "Chromium";v="115""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "document",
  		"sec-fetch-mode" -> "navigate",
  		"sec-fetch-site" -> "same-origin",
  		"sec-fetch-user" -> "?1",
  		"upgrade-insecure-requests" -> "1"
  )
  
  private val headers_3 = Map(
  		"accept" -> "application/json, text/javascript, */*; q=0.01",
  		"accept-encoding" -> "gzip, deflate, br",
  		"accept-language" -> "en-US,en;q=0.9",
  		"content-type" -> "application/json; charset=UTF-8",
//  		"origin" -> "https://admin.stagbot.io",
  		"sec-ch-ua" -> """Not/A)Brand";v="99", "Google Chrome";v="115", "Chromium";v="115""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "empty",
  		"sec-fetch-mode" -> "cors",
  		"sec-fetch-site" -> "same-origin",
  		"x-requested-with" -> "XMLHttpRequest"
  )
  
  private val headers_6 = Map(
  		"accept" -> "*/*",
  		"accept-encoding" -> "gzip, deflate, br",
  		"accept-language" -> "en-US,en;q=0.9",
  		"content-type" -> "application/json",
  		"sec-ch-ua" -> """Not/A)Brand";v="99", "Google Chrome";v="115", "Chromium";v="115""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "empty",
  		"sec-fetch-mode" -> "cors",
  		"sec-fetch-site" -> "same-origin",
  		"x-requested-with" -> "XMLHttpRequest"
  )
  
  private val headers_7 = Map(
  		"accept" -> "application/json, text/javascript, */*; q=0.01",
  		"accept-encoding" -> "gzip, deflate, br",
  		"accept-language" -> "en-US,en;q=0.9",
  		"sec-ch-ua" -> """Not/A)Brand";v="99", "Google Chrome";v="115", "Chromium";v="115""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "empty",
  		"sec-fetch-mode" -> "cors",
  		"sec-fetch-site" -> "same-origin",
  		"x-requested-with" -> "XMLHttpRequest"
  )

	// --------------------------------------

	def get_root(): ChainBuilder = {
		exec(
			http("GET /")
				.get("https://#{ADMINHOST}/")
				.headers(headers_0)
		)
	}

	def post_is2FA(): ChainBuilder = {
		exec(
			http("POST /auth/is2FA")
				.post("https://#{ADMINHOST}/auth/is2FA")
				.headers(headers_3)
				.body(StringBody(string = "{\"email\":\"#{ADMINNAME}@#{ADMINADDRESS}\"," +
					"\"password\":\"#{ADMINPASSWORD}\"," +
					"\"is2fa\":false}"))
		)
	}

	def post_authLogin(): ChainBuilder = {
		exec(
			http("POST /auth/login")
				.post("https://#{ADMINHOST}/auth/login")
				.headers(headers_3)
				.body(StringBody(string = "{\"email\":\"#{ADMINNAME}@#{ADMINADDRESS}\"," +
					"\"password\":\"#{ADMINPASSWORD}\"," +
					"\"accessCode\":null,\"accessToken\":null," +
					"\"twoFactorToken\":null,\"passwordExpiresOn\":null}"))
		)
	}

	def post_byEmail(): ChainBuilder = {
		exec(
			http("POST /domainAdmins/byEmail")
				.post("https://#{ADMINHOST}/domainAdmins/byEmail")
				.headers(headers_3)
				.body(StringBody(string = "{\"email\":\"#{ADMINNAME}@#{ADMINADDRESS}\"," +
					"\"firstName\":null,\"lastName\":null," +
					"\"password\":null,\"companyId\":null," +
					"\"countryCode\":null,\"phoneNumber\":null," +
					"\"acceptsCalls\":false,\"acceptsSMS\":false," +
					"\"ssoAdmin\":false,\"role\":null,\"identityProvider\":null}"))
		)
	}

	def get_environment(): ChainBuilder = {
		exec(
			http("GET /auth/environment")
				.get("https://#{ADMINHOST}/auth/environment")
				.headers(headers_6)
		)
	}

	def get_companies(): ChainBuilder = {
		exec(
			http("GET /companies")
				.get("https://#{ADMINHOST}/companies?draw=1&columns%5B0%5D%5Bdata%5D=function&columns%5B0%5D%5Bname%5D=&columns%5B0%5D%5Bsearchable%5D=true&columns%5B0%5D%5Borderable%5D=true&columns%5B0%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B0%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B1%5D%5Bdata%5D=licenseType&columns%5B1%5D%5Bname%5D=&columns%5B1%5D%5Bsearchable%5D=true&columns%5B1%5D%5Borderable%5D=true&columns%5B1%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B1%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B2%5D%5Bdata%5D=id&columns%5B2%5D%5Bname%5D=&columns%5B2%5D%5Bsearchable%5D=true&columns%5B2%5D%5Borderable%5D=true&columns%5B2%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B2%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B3%5D%5Bdata%5D=companyUuid&columns%5B3%5D%5Bname%5D=&columns%5B3%5D%5Bsearchable%5D=true&columns%5B3%5D%5Borderable%5D=true&columns%5B3%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B3%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B4%5D%5Bdata%5D=industry&columns%5B4%5D%5Bname%5D=&columns%5B4%5D%5Bsearchable%5D=true&columns%5B4%5D%5Borderable%5D=true&columns%5B4%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B4%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B5%5D%5Bdata%5D=subDomain&columns%5B5%5D%5Bname%5D=&columns%5B5%5D%5Bsearchable%5D=true&columns%5B5%5D%5Borderable%5D=true&columns%5B5%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B5%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B6%5D%5Bdata%5D=details&columns%5B6%5D%5Bname%5D=&columns%5B6%5D%5Bsearchable%5D=true&columns%5B6%5D%5Borderable%5D=false&columns%5B6%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B6%5D%5Bsearch%5D%5Bregex%5D=false&order%5B0%5D%5Bcolumn%5D=0&order%5B0%5D%5Bdir%5D=asc&start=0&length=25&search%5Bvalue%5D=&search%5Bregex%5D=false&sortField=name&sortOrder=asc&searchColumns%5B%5D=name&searchColumns%5B%5D=id&searchColumns%5B%5D=legalName&searchColumns%5B%5D=abbreviation&deletedCompanies=false&_=1692107985795")
				.headers(headers_7)
		)
	}

	// --------------------------------------

	val csvAdmins = csv(filePath = "data/perform-admins.csv").circular

	// --------------------------------------

	val adminLoginFullScn = scenario("Admin App Full User Login")
		.feed(csvAdmins)
    .exec(get_root())
    .pause(5)
		.exec(post_is2FA())
		.pause(1)
		.exec(post_authLogin())
		.pause(1)
		.exec(post_byEmail())
		.pause(1)
		.exec(get_environment())
		.pause(1)
		.exec(get_companies())

	val adminLoginMinimalScn = scenario("Admin Authenticate Only")
		.feed(csvAdmins)
		.exec(post_authLogin())
	
}
