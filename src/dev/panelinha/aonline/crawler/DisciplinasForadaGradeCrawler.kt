package dev.panelinha.aonline.crawler

import dev.panelinha.aonline.dtos.DisciplinasForadaGradeDTO
import dev.panelinha.aonline.models.User
import dev.panelinha.aonline.crawler.AuthenticatedCrawler

class DisciplinasForadaGradeCrawler(user: User): AuthenticatedCrawler(user), IScraper{
    override fun scrap(): DisciplinasForadaGradeDTO{

        val crawler = this.crawler("http://online.iesb.br/aonline/historico.asp")
        val document = crawler.get()
        val disciplinasForadaGradeDTO = DisciplinasForadaGradeDTO()

        document.select("#ctnTabPagina2 > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > b:nth-child(6) > b:nth-child(1) > b:nth-child(30) > b:nth-child(1) > b:nth-child(5) > b:nth-child(1) > b:nth-child(5) > b:nth-child(1) > b:nth-child(5) > b:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:not(:first-child):not(:last-child)"
        ).forEach {elemento ->
            val linha = elemento.select("td").map { coluna -> coluna.text()}
            if(linha.size == 8)
                disciplinasForadaGradeDTO.addDisciplina(linha)
        }

        return disciplinasForadaGradeDTO
    }

}