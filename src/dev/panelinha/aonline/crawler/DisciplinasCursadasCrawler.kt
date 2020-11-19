package dev.panelinha.aonline.crawler

import dev.panelinha.aonline.models.User
import dev.panelinha.aonline.dtos.DisciplinasCursadasDTO

class DisciplinasCursadasCrawler(user: User) : AuthenticatedCrawler(user), IScraper {
    override fun scrap(): DisciplinasCursadasDTO {
        val crawler = this.crawler("http://online.iesb.br/aonline/historico.asp")
        val document = crawler.get()
        val disciplinasCursadasDTO = DisciplinasCursadasDTO()

        document.select(
            "#ctnTabPagina2 > table > tbody > tr > td > b > b > table > tbody > tr:not(:first-child):not(:last-child)"
        ).forEach { elemento ->
            val linha = elemento.select("td").map { coluna -> coluna.text() }
            if (linha.size == 14)
                disciplinasCursadasDTO.addDisciplina(linha)
        }

        return disciplinasCursadasDTO
    }
}
