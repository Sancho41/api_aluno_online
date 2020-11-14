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
                    val user = call.principal<User>()!!
                    call.respond(service.disciplinasCursadas(user))
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to "não foi possível encontrar nenhuma disciplina cursada"))
                }
            }

            get("disciplinas-matriculadas") {
                try {
                    val user = call.principal<User>()!!
                    val d = service.disciplinasMatriculadas(user)
                    call.respond(d)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to "não foi possível encontrar nenhuma disciplina matriculada"))
                }
            }

            get("disciplinas-pendentes") {
                try {
                    val user = call.principal<User>()!!
                    call.respond(service.disciplinasPendentes(user))
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to "não foi possível encontrar nenhuma disciplina pendente"))
                }
            }

            get("disciplinas-complementares") {
                try {
                    val user = call.principal<User>()!!
                    call.respond(service.disciplinasComplementares(user))
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to "não foi possível encontrar nenhuma disciplina complementar"))
                }
            }

            get("disciplinas-foradagrade") {
                try {
                    val user = call.principal<User>()!!
                    call.respond(service.disciplinasForadaGrade(user))
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to "não foi possível encontrar nenhuma disciplina fora da grade"))
                }
            }
        }
    }
}