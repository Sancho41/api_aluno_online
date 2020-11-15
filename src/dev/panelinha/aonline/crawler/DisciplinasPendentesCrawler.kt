package dev.panelinha.aonline.crawler

import dev.panelinha.aonline.models.User
import dev.panelinha.aonline.dtos.DisciplinasPendentesDTO

class DisciplinasPendentesCrawler(user: User): AuthenticatedCrawler(user) {

    override fun scrap(): DisciplinasPendentesDTO {

        val crawler = this.crawler("http://online.iesb.br/aonline/historico.asp")
        val document = crawler.get()
        val disciplinasPendentesDTO = DisciplinasPendentesDTO()

        document.select("#ctnTabPagina2 > table > tbody > tr > td > b > b > b > b > b > b > table > tbody > tr:not(:first-child):not(:last-child)").forEach{
            elemento ->
            val linha = elemento.select("td").map {coluna -> coluna.text()}

            if(linha.size == 5)
                disciplinasPendentesDTO.addDisciplinaP(linha)

        }

        return disciplinasPendentesDTO

    }
}