import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the Licenca entity.
 */
class LicencaGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://127.0.0.1:8080"""

    val httpConf = http
        .baseURL(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connectionHeader("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authentication = Map(
        "Content-Type" -> """application/json""",
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "Authorization" -> "${access_token}"
    )

    val scn = scenario("Test the Licenca entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(401))).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
        .post("/api/authenticate")
        .headers(headers_http_authentication)
        .body(StringBody("""{"username":"admin", "password":"admin"}""")).asJSON
        .check(header.get("Authorization").saveAs("access_token"))).exitHereIfFailed
        .pause(1)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get all licencas")
            .get("/api/licencas")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new licenca")
            .post("/api/licencas")
            .headers(headers_http_authenticated)
            .body(StringBody("""{"id":null, "album":"SAMPLE_TEXT", "andamento":"SAMPLE_TEXT", "compensacaoambiental":null, "dataemissao":"2020-01-01T00:00:00.000Z", "dataentregadocs":"2020-01-01T00:00:00.000Z", "dataexpedicaoprorrogacao1":"2020-01-01T00:00:00.000Z", "dataexpedicaoprorrogacao2":"2020-01-01T00:00:00.000Z", "dataexpedicaoprorrogacao3":"2020-01-01T00:00:00.000Z", "dataoficiolocalpedido":"2020-01-01T00:00:00.000Z", "dataoficiolocalrecebimento":"2020-01-01T00:00:00.000Z", "dataoficioreoficialpedido":"2020-01-01T00:00:00.000Z", "dataoficioreoficialrecebimento":"2020-01-01T00:00:00.000Z", "datapedidoprorrogacao1":"2020-01-01T00:00:00.000Z", "datapedidoprorrogacao2":"2020-01-01T00:00:00.000Z", "datapedidoprorrogacao3":"2020-01-01T00:00:00.000Z", "datavalidadeprorrogacao1":"2020-01-01T00:00:00.000Z", "datavalidadeprorrogacao2":"2020-01-01T00:00:00.000Z", "datavalidadeprorrogacao3":"2020-01-01T00:00:00.000Z", "datavencimento":"2020-01-01T00:00:00.000Z", "datavencimentoatual":"2020-01-01T00:00:00.000Z", "descricao":"SAMPLE_TEXT", "dispensalai":null, "docsentregues":"SAMPLE_TEXT", "eiarima":null, "fcei":"SAMPLE_TEXT", "fceidatapagamento":"2020-01-01T00:00:00.000Z", "fceidataprotocolo":"2020-01-01T00:00:00.000Z", "fceivalor":null, "folder":"SAMPLE_TEXT", "inativo":null, "inventarioflorestal":null, "numero":"SAMPLE_TEXT", "numerolap":"SAMPLE_TEXT", "numerooficioconcessaoprorrogacao1":"SAMPLE_TEXT", "numerooficioconcessaoprorrogacao2":"SAMPLE_TEXT", "numerooficioconcessaoprorrogacao3":"SAMPLE_TEXT", "numerooficiolocalpedido":"SAMPLE_TEXT", "numerooficiolocalrecebimento":"SAMPLE_TEXT", "numerooficiooficialpedido":"SAMPLE_TEXT", "numerooficiooficialrecebimento":"SAMPLE_TEXT", "numerooficioprorrogacao1":"SAMPLE_TEXT", "numerooficioprorrogacao2":"SAMPLE_TEXT", "numerooficioprorrogacao3":"SAMPLE_TEXT", "numeroparecertecnico":"SAMPLE_TEXT", "numeroprocesso":"SAMPLE_TEXT", "observacao":"SAMPLE_TEXT", "pendente":"SAMPLE_TEXT", "prazomes":null, "prazovalidade":"0", "reciboentregadocs":"SAMPLE_TEXT", "usosolo":null, "usosoloobs":"SAMPLE_TEXT"}""")).asJSON
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_licenca_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created licenca")
                .get("${new_licenca_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created licenca")
            .delete("${new_licenca_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(100) over (1 minutes))
    ).protocols(httpConf)
}
