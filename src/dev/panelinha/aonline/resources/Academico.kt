package dev.panelinha.aonline.resources

import dev.panelinha.aonline.services.AcademicoService
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get

fun Route.academico() {
    val service = AcademicoService()

    get("academico/boletim") {
        try {
            val boletim = service.boletim()
            call.respond(boletim)
        } catch (e: Exception) {
            call.respond(mapOf("error" to "não foi possível pegar o boletim"))
        }
    }
}
