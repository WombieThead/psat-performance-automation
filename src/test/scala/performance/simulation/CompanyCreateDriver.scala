package performance.simulation

import io.gatling.core.Predef.Simulation

import io.gatling.core.Predef._
import performance.baseTools.CompanyCreate

class CompanyCreateDriver extends Simulation {

  setUp(CompanyCreate.companyCreateScn.inject(atOnceUsers(1))).protocols(CompanyCreate.httpProtocol)

}
