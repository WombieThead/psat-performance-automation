package performance.baseTools

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.structure.ChainBuilder

import java.time.LocalDate
import scala.util.Random

object UserCreate {

  private val headers_1 = Map(
    "postman-token" -> "59a36899-679f-88a1-2451-83c9497de766",
    "content-type" -> "application/vnd.api+json",
    "cache-control" -> "no-cache",
    "User-Agent" -> "curl/7.47.0"
  )

  // --------------------------------------

  def post_users(): ChainBuilder = {
    exec(
      http("POST /api/rolodex/api/users")
        .post("https://#{ALIAS}.#{HOST}/api/rolodex/api/users")
          .headers(headers_1)
          .header("authorization", value = "#{jwtToken}")
          .body(StringBody(string = "{ " +
            "\"data\": [ #{JSONDATA_NEWUSERS} ], " +
            "\"meta\": { " +
              "\"source\": \"#{SOURCE}\", " +
              "\"dryRun\": \"False\", " +
              "\"batch-number\": #{BATCHNUMBER}, " +
              "\"batch-id\": \"#{BATCHID}\", " +
              "\"correlation_uuid\": \"#{CORRID}\"," +
              "\"batch-count\": #{BATCHES}, " +
              "\"total_user_count\": #{USERSIZE} } }"
            )
          )
    )
  }

  // --------------------------------------

  val csvUsers = csv(filePath = "data/perform-companies.csv").circular

  def randomString(length: Int) = {
    Random.alphanumeric.filter(_.isLower).take(length).mkString
  }

  def randomNumber(length: Int) = {
    Random.alphanumeric.filter(_.isDigit).take(length).mkString
  }

  def genName(baseName: String): String = {
    new String(
      baseName + "-" +
        LocalDate.now.getYear.toString +
        "%02d".format(LocalDate.now.getMonthValue) +
        "%02d".format(LocalDate.now.getDayOfMonth) +
        "-" + randomString(8)
    )
  }

  def initialize(): ChainBuilder = {
    exec(
      session => {
        // Set all vals before session assignment
        // Later we will make these env/config values
        val batchNum = 0
        val maxRequestSize = 200
        val userSize = 1000
        val tagSize = 8
        val source = "userSync"

        val leftover = userSize % maxRequestSize
        val batches = userSize / maxRequestSize + (if (leftover > 0) 1 else 0)

        val corrid = java.util.UUID.randomUUID.toString

        session.set("BATCHNUMBER", batchNum)
          .set("MAXREQUESTSIZE", maxRequestSize)
          .set("USERSIZE", userSize)
          .set("TAGSIZE", tagSize)
          .set("SOURCE", source)
          .set("BATCHES", batches)
          .set("LEFTOVER", leftover)
          .set("CORRID", corrid)
      }
    )
  }

  def initCreateUser(): ChainBuilder = {
    exec {
    session => {
      val tagSize = session("TAGSIZE").as[Int]
      val batches = session("BATCHES").as[Int]
      val leftover = session("LEFTOVER").as[Int]
      val batchNumber = session("BATCHNUMBER").as[Int] + 1
      val batchId = java.util.UUID.randomUUID.toString
      var maxUsers = if ((batchNumber == batches) & (leftover != 0)) leftover else session("MAXREQUESTSIZE").as[Int]

      var jsonData = ""
      for (user <- 1 to maxUsers) {
        val firstName = randomString(6)
        val lastName = randomString(6)
        if (user != 1) jsonData += ","
        jsonData += "{ \"attributes\": { \"firstName\": \"" + firstName +
          "\", \"lastName\": \"" + lastName +
          "\", \"emailAddress\": \"" + firstName + "." + lastName + "@" + session("USERADDRESS").as[String] + "\""
        for (tag <- 1 to tagSize) {
          val tagName = "tag" + tag
          val tagValue = "value" + randomNumber(3)
          jsonData += ", \"" + tagName + "\": \"" + tagValue + "\""
        }
        jsonData += ", \"groups\": [] }, \"relationships\": \"\", \"type\": \"users\" }"
      }
      session.set("JSONDATA_NEWUSERS", jsonData)
        .set("BATCHNUMBER", batchNumber)
        .set("BATCHID", batchId)
    }
    }
  }

  // --------------------------------------

  val createUserScn = scenario("Create Users via API")
    .feed(csvUsers)
    .exec(Authenticate.initialize())
    .exec(Authenticate.post_jwt())
    .exec(initialize())
    .repeat("#{BATCHES}","BATCH") {
      exec(initCreateUser())
        .exec(post_users())
//        .exec { session => println("JSONDATA_NEWUSERS :" + session("JSONDATA_NEWUSERS").as[String]); session }
//        .exec { session => println("SOURCE :" + session("SOURCE").as[String]); session }
//        .exec { session => println("BATCHNUMBER :" + session("BATCHNUMBER").as[String]); session }
//        .exec { session => println("BATCH :" + session("BATCH").as[String]); session }
//        .exec { session => println("BATCHID :" + session("BATCHID").as[String]); session }
//        .exec { session => println("CORRID :" + session("CORRID").as[String]); session }
//        .exec { session => println("BATCHES :" + session("BATCHES").as[String]); session }
//        .exec { session => println("USERSIZE :" + session("USERSIZE").as[String]); session }
    }
//    .exec { session => println("JWT auth token: " + session("jwtToken").as[String]); session }
//    .exec { session => println("MAXREQUESTSIZE :" + session("MAXREQUESTSIZE").as[String]); session }
//    .exec { session => println("BATCHES :" + session("BATCHES").as[String]); session }
//    .exec { session => println("LEFTOVER :" + session("LEFTOVER").as[String]); session }
//    .exec { session => println("ALIAS :" + session("ALIAS").as[String]); session }
//    .exec { session => println("HOST :" + session("HOST").as[String]); session }

}
