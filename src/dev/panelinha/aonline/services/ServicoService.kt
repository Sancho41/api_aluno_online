package dev.panelinha.aonline.services

import dev.panelinha.aonline.models.User
import dev.panelinha.aonline.crawler.AgendamentoCrawler
import dev.panelinha.aonline.crawler.ServicosSolicitadosCrawler
import dev.panelinha.aonline.dtos.AgendamentoDTO
import dev.panelinha.aonline.dtos.ServicosSolicitadosDTO

class ServicoService {
    fun agendamento(user: User): AgendamentoDTO {
        return AgendamentoCrawler(user).scrap()
    }

    fun servicosSolicitados(user: User): ServicosSolicitadosDTO {
        return ServicosSolicitadosCrawler(user).scrap()
    }
}
