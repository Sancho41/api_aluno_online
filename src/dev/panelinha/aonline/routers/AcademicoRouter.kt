package dev.panelinha.dev.panelinha.aonline.routers

import dev.panelinha.aonline.services.AcademicoService
import dev.panelinha.dev.panelinha.aonline.utils.ApiPahts
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

val service = AcademicoService()

fun Route.academicoRouting() {

    route(ApiPahts.ACADEMICO) {
        get("/boletim") {
            try {
                val boletim = service.boletim()
                call.respond(boletim)
            } catch (e: Exception) {
                call.respond(mapOf("error" to "não foi possível pegar o boletim"))
            }
        }

        get("/atividade_complementar") {
            try {
//            val atvCompl = service.atvCompl()
//            call.respond(atvCompl)
            } catch (e: Exception) {
                call.respond(mapOf("error" to "não foi possível encontrar nenhuma atividade complementar"))
            }
        }
    }
}