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
                val upToDate = service.upToDate(user)
                call.respond(ResponseLoginDTO(token, upToDate))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.Forbidden, e)
            }
        }

        post<RegisterDTO>("/register") {
            try {
                val user = service.register(it)
                val upToDate = service.upToDate(user)
                call.respond(HttpStatusCode.Created, ResponseRegisterDTO(user, upToDate))
            } catch (e: Exception) {
                println(e.printStackTrace())
                call.respond(HttpStatusCode.Forbidden, e)
            }
        }

        authenticate {
            post<UpdateUserDTO>("/update") {
                try {
                    val user = call.principal<User>() ?: throw Exception("Unauthorized")
                    val updatedUser = service.update(user, it)
                    val upToDate = service.upToDate(updatedUser)
                    call.respond(HttpStatusCode.OK, ResponseRegisterDTO(updatedUser, upToDate))
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.Forbidden, e)
                }
            }
        }
    }
}
