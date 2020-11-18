package dev.panelinha.aonline.services

import dev.panelinha.aonline.models.User
import dev.panelinha.aonline.crawler.FinanceiroCrawler
import dev.panelinha.aonline.dtos.DatasExtratoDTO
import dev.panelinha.aonline.dtos.FinanceiroDTO

class FinanceiroService {

    fun extratoFin(user: User, datasExtratoDTO: DatasExtratoDTO): FinanceiroDTO {
        return FinanceiroCrawler(user, datasExtratoDTO.toMap()).scrap();
    }
}
