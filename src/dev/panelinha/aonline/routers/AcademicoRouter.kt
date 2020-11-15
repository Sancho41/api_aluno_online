package dev.panelinha.aonline.routers

import dev.panelinha.aonline.models.User
import dev.panelinha.aonline.services.AcademicoService
import dev.panelinha.aonline.utils.ApiPaths
import dev.panelinha.aonline.utils.LoggerBuilder
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.principal
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route


fun Route.academicoRouting() {

    val service = AcademicoService()

    authenticate {
        route(ApiPaths.ACADEMICO) {
            get("/boletim") {
                try {
                    val user = call.principal<User>()!!
                    val boletim = service.boletim(user)
                    call.respond(boletim)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to "não foi possível pegar o boletim"))
                }
            }

            get("/hora_aula") {
                try {
                    val user = call.principal<User>()!!
                    val horaAulas = service.horaAulas(user)
                    call.respond(horaAulas)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to "não foi possível resgatar os horários das aulas"))
                }
            }
        }
    }
}
