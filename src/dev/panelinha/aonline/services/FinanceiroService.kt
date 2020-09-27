package dev.panelinha.dev.panelinha.aonline.services

import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dao.FinanceiroDAO

class FinanceiroService {
    private val DAO = FinanceiroDAO()

    fun extratoFin(user: User): List<Any>{
        return DAO.extratoFin(user)
    }
}