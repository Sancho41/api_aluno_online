package dev.panelinha.aonline.routers

import dev.panelinha.aonline.dtos.UserDTO
import dev.panelinha.aonline.models.User
import dev.panelinha.aonline.dtos.*
import dev.panelinha.aonline.modules.JwtConfig
import dev.panelinha.aonline.services.AuthService
import dev.panelinha.aonline.utils.ApiPaths
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.principal
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.authRouting() {
    val service = AuthService()
    route(ApiPaths.AUTH) {
        post <LoginUserDTO>("/login") {
            try {
                val user = service.login(it)
                user.credenciaisAO?.chave = it.chave ?: "nokey"
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

            get ("/user") {
                try {
                    val user = call.principal<User>()!!
                    call.respond(HttpStatusCode.OK, UserDTO(user))
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.Forbidden, e)
                }
            }
        }
    }
}
