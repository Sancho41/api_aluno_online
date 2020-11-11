package dev.panelinha

import dev.panelinha.aonline.dao.AuthDAO
import dev.panelinha.dev.panelinha.aonline.modules.JwtConfig
import dev.panelinha.dev.panelinha.aonline.routers.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.auth.*
import io.ktor.auth.jwt.jwt
import io.ktor.gson.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.litote.kmongo.json

fun main(args: Array<String>) {
    val port = System.getenv("PORT")?.toInt() ?: 8080

    embeddedServer(Netty, port) {
        install(CORS) {
            method(HttpMethod.Options)
            method(HttpMethod.Put)
            method(HttpMethod.Delete)
            method(HttpMethod.Patch)
            header(HttpHeaders.Authorization)
            allowCredentials = true
            anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
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
