package dev.panelinha.aonline.routers

import dev.panelinha.aonline.dtos.ExceptionDTO
import dev.panelinha.aonline.models.User
import dev.panelinha.aonline.services.ServicoService
import dev.panelinha.aonline.utils.ApiPaths
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.principal
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route


fun Route.servicoRouting() {

    val service = ServicoService()
    authenticate {
        route(ApiPaths.SERVICO) {

            get("/agendamento") {
                try {
                    val user = call.principal<User>()!!
                    val agendamento = service.agendamento(user)
                    call.respond(agendamento)

                } catch (e: Exception) {

                    call.respond(HttpStatusCode.BadRequest ,
                        ExceptionDTO("Não foi possivel recuperar os agendamentos")
                    )
                }
            }

            get("/servicos-solicitados") {
                try {
                    val user = call.principal<User>()!!
                    val servicosSolicitados = service.servicosSolicitados(user)
                    call.respond(servicosSolicitados)

                } catch (e: Exception) {

                    call.respond(HttpStatusCode.BadRequest ,
                            ExceptionDTO("Não foi possivel recuperar os serviços solicitados")
                    )
                }
            }
        }
    }
}