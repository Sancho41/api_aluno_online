package dev.panelinha.dev.panelinha.aonline.routers

import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.services.HistoricoService
import dev.panelinha.dev.panelinha.aonline.utils.ApiPaths
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.principal
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

fun Route.historicoRouting() {
    val service = HistoricoService()

    authenticate {
        route(ApiPaths.HISTORICO) {
            get("disciplinas-cursadas") {
                try {
                    val user = call.principal<User>() ?: throw Exception("Unauthorized")
                    call.respond(service.disciplinasCursadas(user))
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            get("disciplinas-matriculadas") {
                try {
                    val user = call.principal<User>() ?: throw Exception("Unauthorized")
                    val d = service.disciplinasMatriculadas(user)
                    call.respond(d)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            get("disciplinas-pendentes") {
                try {
                    val user = call.principal<User>() ?: throw Exception("Unauthorized")
                    call.respond(service.disciplinasPendentes(user))
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}