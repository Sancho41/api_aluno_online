package dev.panelinha.dev.panelinha.aonline.routers

import dev.panelinha.dev.panelinha.aonline.utils.ApiPahts
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.authRouting() {
    route(ApiPahts.AUTH) {
        post ("/register") {

        }
    }
}