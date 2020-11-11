package dev.panelinha.dev.panelinha.aonline.crawler

import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dtos.DisciplinasMatriculadasDTO

class DisciplinasMatriculadasCrawler(user: User) : AuthenticatedCrawler(user) {
    override fun scrap(): DisciplinasMatriculadasDTO{
        val crawler = this.crawler("http://online.iesb.br/aonline/historico.asp")
        val document = crawler.get()
        val disciplinasMatriculadasDTO = DisciplinasMatriculadasDTO()

        document.select("#ctnTabPagina2 > table > tbody > tr > td > b > b > b > b > table > tbody > tr:not(:first-child):not(:last-child)"
        ).forEach { elemento ->
            val linha = elemento.select("td").map { coluna -> coluna.text() }

            if(linha.size == 8)
                disciplinasMatriculadasDTO.addDisciplina(linha)
        }

        return disciplinasMatriculadasDTO

    }


}