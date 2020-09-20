package dev.panelinha.dev.panelinha.aonline.routers

import dev.panelinha.dev.panelinha.aonline.dtos.LoginUserDTO
import dev.panelinha.dev.panelinha.aonline.modules.JwtConfig
import dev.panelinha.dev.panelinha.aonline.services.AuthService
import dev.panelinha.dev.panelinha.aonline.utils.ApiPaths
import io.ktor.application.call
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
                val token = JwtConfig.generateToken(service.login(it));
                call.respond(mapOf("token" to token))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.Forbidden, e)
            }
        }
    }
}
