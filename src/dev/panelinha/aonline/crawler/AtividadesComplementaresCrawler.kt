package dev.panelinha.aonline.crawler

import dev.panelinha.aonline.models.User
import dev.panelinha.aonline.dtos.AtividadesComplementaresDTO

class AtividadesComplementaresCrawler(user: User) : AuthenticatedCrawler(user) {
    override val data: Map<String, String> = mapOf()
    override fun scrap(): AtividadesComplementaresDTO {
        val crawler = this.crawler("http://online.iesb.br/aonline/historico.asp")
        val document = crawler.get()
        val atividadesComplementaresDTO = AtividadesComplementaresDTO()

        document.select(
                "#ctnTabPagina2 > table > tbody > tr > td > b > b > b > b > b > b > b > b > table > tbody > tr:not(:first-child)[class^='tr']"
        ).forEach { elemento ->
            val linha = elemento.select("td").map { coluna -> coluna.text() }
            if (linha.size == 5)
                atividadesComplementaresDTO.addDisciplina(linha)
        }

        return atividadesComplementaresDTO
    }

}