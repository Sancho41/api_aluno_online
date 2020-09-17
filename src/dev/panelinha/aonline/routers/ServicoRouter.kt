package dev.panelinha.dev.panelinha.aonline.routers

import dev.panelinha.dev.panelinha.aonline.services.ServicoService
import dev.panelinha.dev.panelinha.aonline.utils.ApiPaths
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route


fun Route.servicoRouting() {

    val service = ServicoService()

    route(ApiPaths.SERVICO) {

        get("/agendamento") {
            try {

                val agendamento = service.agendamento()
                call.respond(agendamento)

            }
            catch (e: Exception){

                call.respond(mapOf("error" to "não foi possível pegar os agendamentos"))
            }
        }
    }


}