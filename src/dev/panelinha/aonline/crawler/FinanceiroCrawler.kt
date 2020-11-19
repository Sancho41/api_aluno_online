package dev.panelinha.aonline.crawler

import dev.panelinha.aonline.models.User
import dev.panelinha.aonline.dtos.FinanceiroDTO

class FinanceiroCrawler(user: User, val data: Map<String, String>): AuthenticatedCrawler(user), IScraper  {
    override fun scrap(): FinanceiroDTO{
        val crawler = this.crawler("http://online.iesb.br/aonline/extrato_financeiro.asp")
        val document = crawler
                .data(data)
                .post()
        val financeiroDTO = FinanceiroDTO()

        document.select(
                "#ctnTabPagina2 > table > tbody > tr > td > table:nth-child(6) > tbody > tr:not(:first-child):not(:nth-child(2))"
        ).forEach { elemento ->
            val linha = elemento.select("td").map { coluna -> coluna.text() }
            if (linha.size == 14 )
                financeiroDTO.addExtrato(linha)
        }

        return financeiroDTO
    }
}