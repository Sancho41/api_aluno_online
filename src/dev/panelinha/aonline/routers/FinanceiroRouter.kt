package dev.panelinha.aonline.routers

import dev.panelinha.aonline.dtos.ExceptionDTO
import dev.panelinha.aonline.models.User
import dev.panelinha.aonline.dtos.DatasExtratoDTO
import dev.panelinha.aonline.services.FinanceiroService
import dev.panelinha.aonline.utils.ApiPaths
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.principal
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.financeiroRouter() {
    val service = FinanceiroService()

    authenticate {


        route(ApiPaths.FINANCEIRO) {
            post<DatasExtratoDTO>("/extrato-financeiro") {
                try {
                    val user = call.principal<User>()!!
                    val extrato = service.extratoFin(user, it)
                    call.respond(HttpStatusCode.OK, extrato)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.Forbidden, ExceptionDTO("Não foi possivel recuperar o extrato financeiro"))
                }
            }
        }
    }
}