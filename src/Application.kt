package dev.panelinha

import dev.panelinha.aonline.dao.AuthDAO
import dev.panelinha.dev.panelinha.aonline.modules.JwtConfig
import dev.panelinha.dev.panelinha.aonline.routers.academicoRouting
import dev.panelinha.dev.panelinha.aonline.routers.authRouting
import dev.panelinha.dev.panelinha.aonline.routers.servicoRouting
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.auth.*
import io.ktor.auth.jwt.jwt
import io.ktor.gson.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
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
                val login = it.payload.getClaim("login").asString()
                if (login != null) {
                    val authDAO = AuthDAO()
                    authDAO.getUserByLogin(login)
                }
                else null
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
    }
}
