package dev.panelinha.aonline

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.LoggerContext
import dev.panelinha.aonline.dao.AuthDAO
import dev.panelinha.dev.panelinha.aonline.modules.JwtConfig
import dev.panelinha.dev.panelinha.aonline.routers.*
import io.ktor.application.ApplicationCallPipeline
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.jwt
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.request.httpMethod
import io.ktor.response.header
import io.ktor.response.respond
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.slf4j.LoggerFactory

fun main(args: Array<String>) {
    val port = System.getenv("PORT")?.toInt() ?: 8080

    val loggerContext: LoggerContext = LoggerFactory.getILoggerFactory() as LoggerContext
    val rootLogger: Logger = loggerContext.getLogger("org.mongodb.driver")
    rootLogger.level = Level.OFF

    val application = embeddedServer(Netty, port) {
        install(CORS) {
            method(HttpMethod.Options)
            method(HttpMethod.Put)
            method(HttpMethod.Delete)
            method(HttpMethod.Patch)
            header(HttpHeaders.Authorization)
            header(HttpHeaders.AccessControlAllowOrigin)
            allowCredentials = true
            anyHost()


            intercept(ApplicationCallPipeline.Setup) {
                if (call.request.httpMethod == HttpMethod.Options) {
                    call.response.header("Access-Control-Allow-Origin", "*")
                    call.response.header("Access-Control-Allow-Headers", "*")
                    call.respond(HttpStatusCode.OK)
                    return@intercept finish()
                }
            }
        }

        install(Authentication) {
            jwt {
                verifier(JwtConfig.verifier)
                realm = "dev.panelinha"
                validate {
                    val email = it.payload.getClaim("email").asString()
                    val chave = it.payload.getClaim("chave").asString()
                    if (email != null) {
                        val authDAO = AuthDAO()
                        authDAO.getUserByEmail(email, chave)
                    } else null
                }
            }
        }

        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
            }
        }

        install(CallLogging)

        val client = HttpClient(Apache) {
        }

        routing {
            authRouting()
            academicoRouting()
            servicoRouting()
            historicoRouting()
            financeiroRouter()
        }
    }.start(wait = true)
}
