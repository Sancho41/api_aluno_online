package dev.panelinha.dev.panelinha.aonline.routers

import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dtos.ExtratoDTO
import dev.panelinha.dev.panelinha.aonline.modules.JwtConfig
import dev.panelinha.dev.panelinha.aonline.services.FinanceiroService
import dev.panelinha.dev.panelinha.aonline.utils.ApiPaths
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

    route(ApiPaths.AUTH) {
        post <ExtratoDTO>("/extratoFinanceiro") {
            try {

            } catch (e: Exception) {
                call.respond(HttpStatusCode.Forbidden, e)
            }
        }
    }
}