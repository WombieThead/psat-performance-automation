package performance.baseTools

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import java.io.{File, FileWriter}

object UserList {

  private val httpProtocol = http
    .inferHtmlResources(AllowList(), DenyList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*\.svg""", """.*detectportal\.firefox\.com.*"""))
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")

  private val headers_9 = Map(
  		"accept" -> "application/vnd.api+json",
  		"accept-encoding" -> "gzip, deflate, br",
  		"accept-language" -> "en-US,en;q=0.9",
  		"authorization" -> "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2OTY1MTU2MDIsImlzcyI6ImF1dGhTZXJ2ZXIiLCJleHAiOjE2OTY1MTYyMDIsInN1YiI6IjQwNDUyNzc1IiwianRpIjoiODRjMmQwMjItZTU1OS00MzQ4LWI5MjItNmRlM2NhOTBlYTJmIiwidHlwZSI6IlVzZXIiLCJyb2xlcyI6WyJTdXBlckFkbWluIiwiVXNlciJdLCJjb21wYW55SWQiOjQ4NTQ2MCwiYXVkaXRJZCI6IjRkNWYwNzM2LThlMzgtNDhkZi05ODVjLTBlZjk5Y2FmY2M2ZSIsImRvbWFpbiI6ImptZXRlcnZ1eWd5Y2h5Y28iLCJ0ZW5hbnRSb2xlcyI6eyJVc2VyIjpbMTU1MTI5XX19.8APCnZ6nh74K1Hq_gqYZQHgh9R7NURGMloYnrONP2p4OpmNNVh1UvVyQv24Z0u0jUtKBDFFC4oHAvwayuOLLlQ",
  		"sec-ch-ua" -> """Google Chrome";v="117", "Not;A=Brand";v="8", "Chromium";v="117""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "empty",
  		"sec-fetch-mode" -> "cors",
  		"sec-fetch-site" -> "same-origin",
  		"x-requested-with" -> "XMLHttpRequest"
  )

	// --------------------------------------

	def get_users(): ChainBuilder = {
		exec(
			http("GET /api/rolodex/api/users")
				.get("https://#{ALIAS}.#{HOST}/api/rolodex/api/users?col%5B%5D=emailAddress&col%5B%5D=firstName&col%5B%5D=lastName&col%5B%5D=created&col%5B%5D=modified&order=asc&page_number=#{PAGENUMBER}&page_size=200&sort=emailAddress&sortKeyAscend=asc&sortKeyDescend=desc")
				.headers(headers_9)
				.header("authorization", value = "#{jwtToken}")
				.check(jsonPath("$.data[*].attributes.emailAddress").findAll.saveAs("GET_EMAILS"))
				.check(jsonPath("$.data[*].attributes.id").findAll.saveAs("GET_USERIDS"))
				.check(jsonPath("$.meta.page_number").saveAs("PAGENUMBER"))
				.check(jsonPath("$.meta.total_pages").saveAs("TOTALPAGES"))
		)
	}

	// --------------------------------------

	def initialize(): ChainBuilder = {
		exec { session => {
			val alias = session("ALIAS").as[String]

			val fileWriter = new FileWriter(new File("company-stage-" + alias + ".csv"),false)
			fileWriter.write("ALIAS,HOST,COMPANYID,COMPANYUUID,USERADDRESS,USERNAME,USERID,PASSWORD\n")
			fileWriter.close()

			val notDone = true
			val nextPage = 1
			session.set("NOTDONE", notDone).set("PAGENUMBER", nextPage)
		}
		}
	}

	def appendCSVFile(): ChainBuilder = {
		exec { session => {
			val alias         = session("ALIAS").as[String]
			val host          = session("HOST").as[String]
			val companyID     = session("COMPANYID").as[String]
			val companyUuid   = session("COMPANYUUID").as[String]
			val userAddresses = session("GET_EMAILS").as[Vector[String]]
			val userIDs       = session("GET_USERIDS").as[Vector[String]]
			val password      = session("PASSWORD").as[String]

			val fileWriter = new FileWriter(new File("company-stage-" + alias + ".csv"), true)

			for ( i <- userAddresses.indices ) {
				val address = userAddresses(i).split("@")
				fileWriter.write(alias + "," + host + "," + companyID + "," + companyUuid + "," + address(1) + "," + address(0) + "," + userIDs(i) + "," + password + "\n")
			}
			fileWriter.close()

			val currentPage = session("PAGENUMBER").as[Int]
			val lastPage = session("TOTALPAGES").as[Int]
			val notDone  = currentPage < lastPage
			val nextPage = currentPage + 1
			session.set("NOTDONE", notDone).set("PAGENUMBER",nextPage)
		}}
	}

	// --------------------------------------

	val listUserScn = scenario("List users via API")
		.feed(Authenticate.csvCompanies)
		.exec(Authenticate.initialize())
		.exec(Authenticate.post_jwt())
		.exec(initialize())
		.doWhile("#{NOTDONE}") {
			exec(get_users())
			.exec(appendCSVFile())
		}
//		.exec { session => println("USER EMAILS: " + session("GET_EMAILS").as[String]); session }
//		.exec { session => println("USER IDS: " + session("GET_USERIDS").as[String]); session }
}
