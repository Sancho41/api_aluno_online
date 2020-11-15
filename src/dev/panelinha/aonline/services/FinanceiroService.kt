package dev.panelinha.aonline.services

import dev.panelinha.aonline.models.User
import dev.panelinha.aonline.crawler.FinanceiroCrawler
import dev.panelinha.aonline.dtos.ExtratoDTO
import dev.panelinha.aonline.dtos.FinanceiroDTO

class FinanceiroService {

    fun extratoFin(user: User, extratoDTO: ExtratoDTO): FinanceiroDTO {
        return FinanceiroCrawler(user).scrap(extratoDTO);
    }
}
