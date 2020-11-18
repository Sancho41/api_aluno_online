package dev.panelinha.aonline.routers

import dev.panelinha.aonline.dtos.ExceptionDTO
import dev.panelinha.aonline.models.User
import dev.panelinha.aonline.services.HistoricoService
import dev.panelinha.aonline.utils.ApiPaths
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
                    val user = call.principal<User>()!!
                    call.respond(service.disciplinasCursadas(user))
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, ExceptionDTO("Não foi possivel recuperar as disciplinas cursadas"))
                }
            }

            get("disciplinas-matriculadas") {
                try {
                    val user = call.principal<User>()!!
                    val d = service.disciplinasMatriculadas(user)
                    call.respond(d)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, ExceptionDTO("Não foi possivel recuperar as disciplinas matriculadas"))
                }
            }

            get("disciplinas-pendentes") {
                try {
                    val user = call.principal<User>()!!
                    call.respond(service.disciplinasPendentes(user))
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, ExceptionDTO("Não foi possivel recuperar as disciplinas pendentes"))
                }
            }

            get("atividades-complementares") {
                try {
                    val user = call.principal<User>()!!
                    call.respond(service.atividadesComplementares(user))
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, ExceptionDTO("Não foi possivel recuperar as atividades complementares"))
                }
            }

            get("disciplinas-fora-da-grade") {
                try {
                    val user = call.principal<User>()!!
                    call.respond(service.disciplinasForadaGrade(user))
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, ExceptionDTO("Não foi possivel recuperar as disciplinas fora de grade"))
                }
            }
        }
    }
}