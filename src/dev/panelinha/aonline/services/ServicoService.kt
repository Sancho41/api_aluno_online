package dev.panelinha.aonline.services

import dev.panelinha.aonline.models.User
import dev.panelinha.aonline.crawler.AgendamentoCrawler
import dev.panelinha.aonline.dtos.AgendamentoDTO

class ServicoService {
    fun agendamento(user: User): AgendamentoDTO {
        return AgendamentoCrawler(user).scrap()
    }
}
