package dev.panelinha.aonline.resources

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.helloWorld() {
    get("/") {
        call.respond(mapOf("hello" to "World"))
    }
}