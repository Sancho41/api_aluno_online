package dev.panelinha.dev.panelinha.aonline.services

import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dao.ServicoDAO

class ServicoService {

    private val dao = ServicoDAO()

    fun agendamento(user: User): List<Map<String, String>>{

        return dao.agendamentos(user)

    }
}