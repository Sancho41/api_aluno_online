package dev.panelinha.aonline.resources

import dev.panelinha.aonline.services.AcademicoService
import dev.panelinha.aonline.services.AtvComplementar
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get

fun Route.academico() {
    val service = AcademicoService()
    val service2 = AtvComplementar() //nome provisorio

    get("academico/boletim") {
        try {
            val boletim = service.boletim()
            call.respond(boletim)
        } catch (e: Exception) {
            call.respond(mapOf("error" to "não foi possível pegar o boletim"))
        }
    }

    get("academico/atvCompl") {
        try {
            val atvCompl = service.atvCompl()
            call.respond(atvCompl)
        } catch (e: Exception) {
            call.respond(mapOf("error" to "não foi possível encontrar nenhuma atividade complementar"))
        }
    }
}
