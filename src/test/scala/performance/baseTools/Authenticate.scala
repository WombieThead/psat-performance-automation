package performance.baseTools

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import io.gatling.core.structure.ChainBuilder

object Authenticate {

  private val httpProtocol = http
		.inferHtmlResources(AllowList(), DenyList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*\.svg""", """.*detectportal\.firefox\.com.*"""))
    .acceptHeader("application/vnd.api+json")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("en-US,en;q=0.9")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
  
  private val headers_0 = Map(
  		"accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
  		"sec-ch-ua" -> """Not/A)Brand";v="99", "Google Chrome";v="115", "Chromium";v="115""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "document",
  		"sec-fetch-mode" -> "navigate",
  		"sec-fetch-site" -> "none",
  		"sec-fetch-user" -> "?1",
  		"upgrade-insecure-requests" -> "1"
  )

  private val headers_2 = Map(
  		"sec-ch-ua" -> """Not/A)Brand";v="99", "Google Chrome";v="115", "Chromium";v="115""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "empty",
  		"sec-fetch-mode" -> "cors",
  		"sec-fetch-site" -> "same-origin",
  		"x-requested-with" -> "XMLHttpRequest"
  )
  
  private val headers_3 = Map(
  		"accept" -> "*/*",
  		"sec-ch-ua" -> """Not/A)Brand";v="99", "Google Chrome";v="115", "Chromium";v="115""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "manifest",
  		"sec-fetch-mode" -> "cors",
  		"sec-fetch-site" -> "same-origin"
  )
  
  private val headers_7 = Map(
  		"accept" -> "application/json;charset=UTF-8",
  		"content-type" -> "application/json",
  		"refresh-authorization" -> "undefined",
  		"sec-ch-ua" -> """Not/A)Brand";v="99", "Google Chrome";v="115", "Chromium";v="115""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "empty",
  		"sec-fetch-mode" -> "cors",
  		"sec-fetch-site" -> "same-origin"
  )
  
  private val headers_8 = Map(
  		"accept" -> "*/*",
  		"content-type" -> "application/vnd.api+json",
//  		"origin" -> "https://josnguyenmta.ws01-stagbot.io",
  		"sec-ch-ua" -> """Not/A)Brand";v="99", "Google Chrome";v="115", "Chromium";v="115""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "empty",
  		"sec-fetch-mode" -> "cors",
  		"sec-fetch-site" -> "same-origin"
  )
  
  private val headers_9 = Map(
			"sec-ch-ua" -> """Not/A)Brand";v="99", "Google Chrome";v="115", "Chromium";v="115""",
			"sec-ch-ua-mobile" -> "?0",
			"sec-ch-ua-platform" -> "macOS",
			"sec-fetch-dest" -> "empty",
			"sec-fetch-mode" -> "cors",
			"sec-fetch-site" -> "same-origin",
			"x-requested-with" -> "XMLHttpRequest"
	)

	private val headers_12 = Map(
  		"content-type" -> "application/vnd.api+json",
  		"refresh-authorization" -> "undefined",
  		"sec-ch-ua" -> """Not/A)Brand";v="99", "Google Chrome";v="115", "Chromium";v="115""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "empty",
  		"sec-fetch-mode" -> "cors",
  		"sec-fetch-site" -> "same-origin"
  )
  
  private val headers_16 = Map(
  		"accept" -> "*/*",
  		"content-type" -> "text/plain;charset=UTF-8",
//  		"origin" -> "https://josnguyenmta.ws01-stagbot.io",
  		"sec-ch-ua" -> """Not/A)Brand";v="99", "Google Chrome";v="115", "Chromium";v="115""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "empty",
  		"sec-fetch-mode" -> "cors",
  		"sec-fetch-site" -> "same-site"
  )
  
  private val headers_17 = Map(
  		"accept" -> "*/*",
  		"content-type" -> "application/json",
//  		"origin" -> "https://josnguyenmta.ws01-stagbot.io",
  		"sec-ch-ua" -> """Not/A)Brand";v="99", "Google Chrome";v="115", "Chromium";v="115""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "macOS",
  		"sec-fetch-dest" -> "empty",
  		"sec-fetch-mode" -> "cors",
  		"sec-fetch-site" -> "same-origin"
  )

	// --------------------------------------

	def get_login(): ChainBuilder = {
		exec(
			http("GET /login")
				.get("https://#{ALIAS}.#{HOST}/login")
				.headers(headers_0)
		)
	}

	def get_companyLoginProfile(): ChainBuilder = {
		exec(
			http("GET /api/companymanagement/api/companyLoginProfile/ALIAS")
				.get("https://#{ALIAS}.#{HOST}/api/companymanagement/api/companyLoginProfile/#{ALIAS}")
				.headers(headers_2)
		)
	}

	def get_manifest(): ChainBuilder = {
		exec(
			http("GET manifest.json")
				.get("https://#{ALIAS}.#{HOST}/manifest.json")
				.headers(headers_3)
		)
	}

	def get_is2FA(): ChainBuilder = {
		exec(
			http("GET /api/auth/is2FA")
				.get("https://#{ALIAS}.#{HOST}/api/auth/is2FA?email=#{USERNAME}%40#{USERADDRESS}")
				.headers(headers_7)
		)
	}

	def post_jwt(): ChainBuilder = {
		exec(
			http("POST /api/auth/api/jwt")
				.post("https://#{ALIAS}.#{HOST}/api/auth/api/jwt")
				.headers(headers_8)
				.body(StringBody(string = "{\"data\":{\"attributes\":" +
					"{\"token\":\"#{ENCODECRED}\"}," +
					"\"id\":\"\",\"type\":\"user\"}}"))
				.check(jsonPath(path = "$.data.attributes.token").exists.saveAs(key = "jwtToken"))
				.check(jsonPath(path = "$.data.attributes.refresh").exists.saveAs(key = "jwtRefresh"))
		)
	}

	def get_user(): ChainBuilder = {
		exec(
			http("GET /api/userprofile/api/user/USER")
				.get("https://#{ALIAS}.#{HOST}/api/userprofile/api/user/#{USERID}")
				.headers(headers_9)
				.header("Authorization", value = "Bearer #{jwtToken}")
		)
	}

	def get_companyProfileV2(): ChainBuilder = {
		exec(
			http("GET /api/companymanagement/api/companyProfileV2/ALIAS")
				.get("https://#{ALIAS}.#{HOST}/api/companymanagement/api/companyProfileV2/#{ALIAS}")
				.headers(headers_9)
				.header("Authorization", value = "Bearer #{jwtToken}")
		)
	}

	def get_companyLicenses(): ChainBuilder = {
		exec(
			http("GET /api/companymanagement/api/companyLicenses/COMPANYID")
				.get("https://#{ALIAS}.#{HOST}/api/companymanagement/api/companyLicenses/#{COMPANYID}")
				.headers(headers_9)
				.header("Authorization", value = "Bearer #{jwtToken}")
		)
	}

	def get_findToasts(): ChainBuilder = {
		exec(
			http("GET /api/notification/api/findToasts")
				.get("https://#{ALIAS}.#{HOST}/api/notification/api/findToasts")
				.headers(headers_12)
				.header("Authorization", value = "Bearer #{jwtToken}")
		)
	}

	// Needs work, isn't required
	def request16(): ChainBuilder = {
		exec(
			http("POST /api/auth/api/customModuleCookies")
				.post("https://custom-module.#{HOST}/api/auth/api/customModuleCookies")
				.headers(headers_16)
				.body(RawFileBody("performance/baseTools/authenticate/0016_request.bin"))
		)
	}

	def post_contentLibraryBrowseMetadata(): ChainBuilder = {
		exec(
			http("POST /api/content/content-library/api")
				.post("https://#{ALIAS}.#{HOST}/api/content/content-library/api")
				.headers(headers_17)
				.header("Authorization", value = "Bearer #{jwtToken}")
				.body(StringBody(
					string = "{\"operationName\":\"BrowseMetadata\",\"variables\":{}," +
						"\"query\":\"query BrowseMetadata {\\n  guillotine {\\n    moduleTypes: query(\\n      first: 1\\n      contentTypes: [\\\"com.psat.contentlibrary:type\\\"]\\n      query: \\\"_name = \\\\\\\"module\\\\\\\"\\\"\\n    ) {\\n      children {\\n        _id\\n        _name\\n        __typename\\n      }\\n      __typename\\n    }\\n    threatAlertTypes: query(\\n      first: 1\\n      contentTypes: [\\\"com.psat.contentlibrary:sam-subtype\\\"]\\n      query: \\\"_name = \\\\\\\"threat-alert\\\\\\\"\\\"\\n    ) {\\n      _id\\n      __typename\\n    }\\n    __typename\\n  }\\n}\\n\"}"))
		)
	}

	// Needs work, isn't required
	def request18(): ChainBuilder = {
		exec(
			http("POST /api/trainingmodules/api/videoCookies")
				.post("https://video-ws.#{HOST}/api/trainingmodules/api/videoCookies")
				.headers(headers_16)
				.body(RawFileBody("performance/baseTools/authenticate/0018_request.bin"))
		)
	}

	def get_licensedModuleMgmt(): ChainBuilder = {
		exec(
			http("GET /api/trainingmodules/api/licensedModuleMgmt")
				.get("https://#{ALIAS}.#{HOST}/api/trainingmodules/api/licensedModuleMgmt")
				.headers(headers_9)
				.header("Authorization", value = "Bearer #{jwtToken}")
		)
	}

	def get_userFavoriteList(): ChainBuilder = {
		exec(
			http("GET /api/mylibrary/api/userFavoriteList")
				.get("https://#{ALIAS}.#{HOST}/api/mylibrary/api/userFavoriteList")
				.headers(headers_12)
				.header("Authorization", value = "Bearer #{jwtToken}")
		)
	}

	def get_userFavoriteListItem(): ChainBuilder = {
		exec(
			http("GET /api/mylibrary/api/userFavoriteListItem")
				.get("https://#{ALIAS}.#{HOST}/api/mylibrary/api/userFavoriteListItem/?filter%5BlistId%5D=4466")
				.headers(headers_12)
				.header("Authorization", value = "Bearer #{jwtToken}")
		)
	}

	def post_contentLibraryNewContent(): ChainBuilder = {
		exec(
			http("POST /api/content/content-library/api")
				.post("https://#{ALIAS}.#{HOST}/api/content/content-library/api")
				.headers(headers_17)
				.header("Authorization", value = "Bearer #{jwtToken}")
				.body(StringBody(
					string = "{\"operationName\":\"NewContent\"," +
						"\"variables\":{\"modulesQuery\":\"(NOT data.hideFromContentLibraryExceptEarlyAdopter = \\\"true\\\" OR _name IN (\\\"avcgloc\\\",\\\"avcgsu\\\",\\\"vap_apt\\\",\\\"avcgsm\\\",\\\"dw_int_call\\\",\\\"veml4_2\\\",\\\"eml4\\\",\\\"veml3_2\\\",\\\"eml3\\\",\\\"avcgbb\\\",\\\"dw_int_door\\\",\\\"ppp\\\",\\\"vcompa2\\\",\\\"compa\\\",\\\"vcompacb\\\",\\\"avci\\\",\\\"avcgco\\\",\\\"eml5\\\",\\\"veml5_2\\\",\\\"dpd\\\",\\\"dw_int_dd\\\",\\\"dw_int6_devdis\\\",\\\"avdbj\\\",\\\"avcgcl\\\",\\\"eml2\\\",\\\"eml\\\",\\\"eml6\\\",\\\"gdpr2\\\",\\\"gdpr\\\",\\\"avcgsn\\\",\\\"hcdo\\\",\\\"hcdp\\\",\\\"hcpv\\\",\\\"dw_int5_hhr\\\",\\\"avphsh\\\",\\\"vito2\\\",\\\"ito\\\",\\\"veml1_2\\\",\\\"eml1\\\",\\\"avcs\\\",\\\"avldr\\\",\\\"avlhr\\\",\\\"itm\\\",\\\"vcompd2\\\",\\\"compd\\\",\\\"map\\\",\\\"mba1\\\",\\\"vmba1_2\\\",\\\"mbd\\\",\\\"vmbd2\\\",\\\"mfa\\\",\\\"avcgps\\\",\\\"avcgpw\\\",\\\"pwm\\\",\\\"pwd\\\",\\\"avcgph\\\",\\\"phy1\\\",\\\"vphy1_2\\\",\\\"pii1\\\",\\\"vpii_rev2\\\",\\\"pii2\\\",\\\"vpii2\\\",\\\"vpw2\\\",\\\"avcgpls\\\",\\\"ptp\\\",\\\"vptp\\\",\\\"vpriv\\\",\\\"priv\\\",\\\"rwr\\\",\\\"vrwr2\\\",\\\"vap_rat\\\",\\\"avcgrl\\\",\\\"vssn2\\\",\\\"ssn\\\",\\\"vswb2\\\",\\\"swb2020\\\",\\\"sbo\\\",\\\"vess2\\\",\\\"ess\\\",\\\"exc2\\\",\\\"vexc2\\\",\\\"avcgss\\\",\\\"avcgda\\\",\\\"eng\\\",\\\"veng2\\\",\\\"eml7\\\",\\\"avclk2\\\",\\\"avclk1\\\",\\\"avsm\\\",\\\"vtra2\\\",\\\"tra\\\",\\\"dw_int_under\\\",\\\"pci\\\",\\\"itu\\\",\\\"urlfun\\\",\\\"url\\\",\\\"usb\\\",\\\"vusb2\\\",\\\"avwf\\\",\\\"avef\\\",\\\"avcgch\\\",\\\"avsat\\\",\\\"vwsa2\\\",\\\"wsa\\\") AND data.earlyAdopter = \\\"true\\\")\",\"threatAlertQuery\":\"data.subtype = \\\"6d7f1821-5b2b-44fa-888a-827850083d26\\\"\"},\"query\":\"fragment NewItemFragment on Content {\\n  _id\\n  displayName\\n  type\\n  publish {\\n    first\\n    from\\n    __typename\\n  }\\n  modifiedTime\\n  __typename\\n}\\n\\nquery NewContent($threatAlertQuery: String!, $modulesQuery: String!) {\\n  guillotine {\\n    newModules: query(\\n      first: 5\\n      query: $modulesQuery\\n      contentTypes: [\\\"com.psat.contentlibrary:module\\\"]\\n      sort: \\\"publish.first DESC\\\"\\n    ) {\\n      ...NewItemFragment\\n      ... on com_psat_contentlibrary_Module {\\n        data {\\n          defaultLocale\\n          localeStrings {\\n            locale\\n            title\\n            description\\n            __typename\\n          }\\n          __typename\\n        }\\n        __typename\\n      }\\n      __typename\\n    }\\n    newThreatAlerts: query(\\n      first: 5\\n      query: $threatAlertQuery\\n      contentTypes: [\\\"com.psat.contentlibrary:awareness-material\\\"]\\n      sort: \\\"publish.first DESC\\\"\\n    ) {\\n      ...NewItemFragment\\n      __typename\\n    }\\n    __typename\\n  }\\n}\\n\"}"))
		)
	}

	def post_contentLibraryFilterData(): ChainBuilder = {
		exec(
			http("POST /api/content/content-library/api")
				.post("https://#{ALIAS}.#{HOST}/api/content/content-library/api")
				.headers(headers_17)
				.header("Authorization", value = "Bearer #{jwtToken}")
				.body(StringBody(
					string = "{\"operationName\":\"FilterDataWithoutHiddenItems\",\"variables\":{}," +
						"\"query\":\"query FilterDataWithoutHiddenItems {\\n  guillotine {\\n    curriculums: query(\\n      first: 100\\n      contentTypes: [\\\"com.psat.contentlibrary:curriculum\\\"]\\n      sort: \\\"displayName ASC\\\"\\n      query: \\\"data.hideFromContentLibrary != 'true'\\\"\\n    ) {\\n      displayName\\n      _id\\n      ... on com_psat_contentlibrary_Curriculum {\\n        data {\\n          hideFromContentLibrary\\n          __typename\\n        }\\n        __typename\\n      }\\n      __typename\\n    }\\n    campaigns: query(\\n      first: 100\\n      contentTypes: [\\\"com.psat.contentlibrary:campaign\\\"]\\n      sort: \\\"displayName ASC\\\"\\n      query: \\\"data.hideFromContentLibrary != 'true'\\\"\\n    ) {\\n      displayName\\n      _id\\n      ... on com_psat_contentlibrary_Campaign {\\n        data {\\n          hideFromContentLibrary\\n          __typename\\n        }\\n        __typename\\n      }\\n      __typename\\n    }\\n    domains: query(\\n      first: 100\\n      contentTypes: [\\\"com.psat.contentlibrary:domain\\\"]\\n      sort: \\\"displayName ASC\\\"\\n    ) {\\n      displayName\\n      _id\\n      __typename\\n    }\\n    levels: query(\\n      first: 100\\n      contentTypes: [\\\"com.psat.contentlibrary:level\\\"]\\n      sort: \\\"displayName ASC\\\"\\n    ) {\\n      displayName\\n      _id\\n      __typename\\n    }\\n    roles: query(\\n      first: 100\\n      contentTypes: [\\\"com.psat.contentlibrary:role\\\"]\\n      sort: \\\"displayName ASC\\\"\\n    ) {\\n      displayName\\n      _id\\n      __typename\\n    }\\n    industries: query(\\n      first: 100\\n      contentTypes: [\\\"com.psat.contentlibrary:industry\\\"]\\n      sort: \\\"displayName ASC\\\"\\n    ) {\\n      displayName\\n      _id\\n      __typename\\n    }\\n    series: query(\\n      first: 100\\n      contentTypes: [\\\"com.psat.contentlibrary:series\\\"]\\n      sort: \\\"displayName ASC\\\"\\n    ) {\\n      displayName\\n      _id\\n      __typename\\n    }\\n    genres: query(\\n      first: 100\\n      contentTypes: [\\\"com.psat.contentlibrary:genre\\\"]\\n      sort: \\\"displayName ASC\\\"\\n    ) {\\n      displayName\\n      _id\\n      __typename\\n    }\\n    types: query(\\n      first: 100\\n      contentTypes: [\\\"com.psat.contentlibrary:type\\\"]\\n      sort: \\\"displayName ASC\\\"\\n    ) {\\n      _id\\n      _name\\n      displayName\\n      children(first: 100) {\\n        _id\\n        _name\\n        displayName\\n        __typename\\n      }\\n      __typename\\n    }\\n    accessibilityTypes: query(\\n      first: 100\\n      contentTypes: [\\\"com.psat.contentlibrary:accessibility-type\\\"]\\n      sort: \\\"displayName ASC\\\"\\n    ) {\\n      displayName\\n      _id\\n      __typename\\n    }\\n    __typename\\n  }\\n}\\n\"}"))
		)
	}

	def get_jobStatus(): ChainBuilder = {
		exec(
			http("GET /api/rolodex/api/job_status")
				.get("https://#{ALIAS}.#{HOST}/api/rolodex/api/job_status")
				.headers(headers_12)
				.header("Authorization", value = "Bearer #{jwtToken}")
		)
	}

	def post_contentLibraryAdaptiveLearning(): ChainBuilder = {
		exec(
			http("POST /api/content/content-library/api")
				.post("https://#{ALIAS}.#{HOST}/api/content/content-library/api")
				.headers(headers_17)
				.header("Authorization", value = "Bearer #{jwtToken}")
				.body(StringBody(
					string = "{\"operationName\":\"AdaptiveLearningQuery\",\"variables\":{}," +
						"\"query\":\"query AdaptiveLearningQuery {\\n  guillotine {\\n    domains: query(\\n      contentTypes: [\\\"com.psat.contentlibrary:domain\\\"]\\n      first: 1000\\n      sort: \\\"data.order ASC, displayName ASC\\\"\\n    ) {\\n      _id\\n      displayName\\n      ... on com_psat_contentlibrary_Domain {\\n        data {\\n          summary\\n          icon {\\n            ... on media_Image {\\n              mediaUrl\\n              __typename\\n            }\\n            ... on media_Vector {\\n              mediaUrl\\n              __typename\\n            }\\n            __typename\\n          }\\n          levels {\\n            level {\\n              _name\\n              __typename\\n            }\\n            __typename\\n          }\\n          __typename\\n        }\\n        __typename\\n      }\\n      __typename\\n    }\\n    modules: query(\\n      contentTypes: [\\\"com.psat.contentlibrary:module\\\"]\\n      first: 1000\\n      query: \\\"data.domain LIKE '*'\\\"\\n    ) {\\n      _id\\n      _name\\n      ... on com_psat_contentlibrary_Module {\\n        data {\\n          domain {\\n            _id\\n            __typename\\n          }\\n          level {\\n            _name\\n            __typename\\n          }\\n          earlyAdopter\\n          hideFromContentLibraryExceptEarlyAdopter\\n          __typename\\n        }\\n        __typename\\n      }\\n      __typename\\n    }\\n    __typename\\n  }\\n}\\n\"}"))
		)
	}

// --------------------------------------

	val csvUsers = csv(filePath = "data/perform-companies.csv").circular

	def encodeCreds(username: String, password: String): String = {
		new String(java.util.Base64.getEncoder.encode((username + ":" + password).getBytes()))
	}

	def initialize(): ChainBuilder = {
		exec {
		session =>
			session.set("ENCODECRED",
				encodeCreds(session("USERNAME").as[String] + "@" + session("USERADDRESS").as[String], session("PASSWORD").as[String]))
		}
	}

	// --------------------------------------

	val userLoginFullScn = scenario("User ember full login with password")
		.feed(csvUsers)
		.exec( initialize())
//		.exec { session => println("USER: " + session("USERNAME").as[String] + "@" + session("USERADDRESS").as[String]); session }
//		.exec { session => println("PASSWORD: " + session("PASSWORD").as[String]); session }
//		.exec { session => println("USERID: " + session("USERID").as[String]); session }
//		.exec { session => println("ENCODECRED: " + session("ENCODECRED").as[String]); session }
		.exec(get_login())
		.pause(2)
		.exec(get_companyLoginProfile())
		.pause(2)
		.exec(get_manifest())
		.pause(2)
		.exec(get_is2FA())
		.pause(2)
		.exec(post_jwt())
		.pause(2)
		.exec(get_user())
		.pause(2)
		.exec(get_companyProfileV2())
		.pause(2)
		.exec(get_companyLicenses())
		.pause(2)
		.exec(get_findToasts())
		.pause(2)
		.exec(get_companyLoginProfile())
		.pause(2)
		.exec(get_companyProfileV2())
    .pause(4)
		.exec(get_user())
		.pause(4)
//		.exec(request16())
//		.pause(4)
		.exec(post_contentLibraryBrowseMetadata())
		.pause(4)
//		.exec(request18())
//		.pause(4)
		.exec(get_licensedModuleMgmt())
		.pause(4)
		.exec(get_userFavoriteList())
		.pause(4)
		.exec(get_userFavoriteListItem())
		.pause(4)
		.exec(post_contentLibraryNewContent())
		.pause(4)
		.exec(post_contentLibraryFilterData())
		.pause(4)
		.exec(get_jobStatus())
		.pause(4)
		.exec(post_contentLibraryAdaptiveLearning())
		.pause(4)
		.exec(get_jobStatus())
    .pause(3)
    .exec(get_findToasts())
    .pause(1)
    .exec(get_jobStatus())

	val userLoginMinimalScn = scenario("JWT Authenticate Only")
		.feed(csvUsers)
		.exec(initialize())
		.exec(post_jwt())
//		.exec { session => println("JWT auth token: " + session("jwtToken").as[String]); session }
//		.exec { session => println("JWT refresh token: " + session("jwtRefresh").as[String]); session }

}
