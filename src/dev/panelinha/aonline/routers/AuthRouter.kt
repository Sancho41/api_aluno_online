package dev.panelinha.dev.panelinha.aonline.routers

import dev.panelinha.dev.panelinha.aonline.dtos.RegisterUserDTO
import dev.panelinha.dev.panelinha.aonline.utils.ApiPaths
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.authRouting() {
    route(ApiPaths.AUTH) {
        post <RegisterUserDTO>("/register") {
            call.respond(it)
        }
    }
}