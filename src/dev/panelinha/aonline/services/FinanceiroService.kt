package dev.panelinha.dev.panelinha.aonline.services

import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dao.FinanceiroDAO
import dev.panelinha.dev.panelinha.aonline.dtos.ExtratoDTO

class FinanceiroService {
    private val DAO = FinanceiroDAO()

    fun extratoFin(user: User, extratoDTO: ExtratoDTO): List<Any>{
        return DAO.extratoFin(user, extratoDTO)
    }
}