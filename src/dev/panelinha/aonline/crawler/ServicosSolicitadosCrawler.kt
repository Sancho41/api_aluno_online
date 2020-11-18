package dev.panelinha.aonline.crawler

import dev.panelinha.aonline.dtos.ServicosSolicitadosDTO
import dev.panelinha.aonline.models.User

class ServicosSolicitadosCrawler(user: User) :AuthenticatedCrawler(user) {
    override val data: Map<String, String> = mapOf()
    override fun scrap(): ServicosSolicitadosDTO {
        val crawler = this.crawler("http://online.iesb.br/aonline/servicos_solicit.asp")
        val document = crawler.get()
        val servicosSolicitadosDTO = ServicosSolicitadosDTO()

        document.select(
                "#ctnTabPagina2 > table > tbody > tr > td > table > tbody > tr:not(:first-child)"
        ).forEach { elemento ->
            val linha = elemento.select("td").map { coluna -> coluna.text() }
            if (linha.size == 8 || linha.size == 7)
                servicosSolicitadosDTO.addServicos(linha)
        }

        return servicosSolicitadosDTO
    }
}