package dev.panelinha.dev.panelinha.aonline.routers

import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dtos.*
import dev.panelinha.dev.panelinha.aonline.modules.JwtConfig
import dev.panelinha.dev.panelinha.aonline.services.AuthService
import dev.panelinha.dev.panelinha.aonline.utils.ApiPaths
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.principal
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.authRouting() {
    val service = AuthService()
    route(ApiPaths.AUTH) {
        post <LoginUserDTO>("/login") {
            try {
                val user = service.login(it)
                val token = JwtConfig.generateToken(user)
                call.respond(ResponseLoginDTO(token))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.Forbidden, e)
            }
        }

        post<RegisterDTO>("/register") {
            try {
                service.register(it)
                call.respond(HttpStatusCode.Created)
            } catch (e: Exception) {
                println(e.printStackTrace())
                call.respond(HttpStatusCode.Forbidden, e)
            }
        }

        authenticate {
            //TODO: Criar rota para mudar senha de usuário.
            post<RegisterAODTO>("/generate") {
                try {
                    val user = call.principal<User>()!!
                    val apiToken = service.registerAlunoOnline(user, it)
                    call.respond(HttpStatusCode.Created, apiToken)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.Forbidden, e)
                }
            }
        }
    }
}
