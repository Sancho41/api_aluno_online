package dev.panelinha.dev.panelinha.aonline.services

import dev.panelinha.dev.panelinha.aonline.dao.ServicoDAO

class ServicoService {

    private val dao = ServicoDAO()

    fun agendamento(): List<Map<String, String>>{

        return dao.agendamentos()

    }
}