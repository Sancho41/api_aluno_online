package dev.panelinha.aonline.crawler

import dev.panelinha.aonline.dtos.DisciplinasMatriculadasDMDTO
import dev.panelinha.aonline.models.User

class DisciplinasMatriculadasDMCrawler(user: User) : AuthenticatedCrawler(user) {
    override fun scrap(): DisciplinasMatriculadasDMDTO {
        val crawler = this.crawler("http://online.iesb.br/aonline/meusdocentes.asp")
        val document = crawler.get()
        val disciplinasMatriculadasDMDTO = DisciplinasMatriculadasDMDTO()

        document.select("#ctnTabPagina2 > table > tbody > tr > td > table:nth-child(2) > tbody > tr:not(:first-child)"
        ).forEach { elemento ->
            val linha = elemento.select("td").map { coluna -> coluna.text() }

            if(linha.size == 5)
                disciplinasMatriculadasDMDTO.addDisciplina(linha)
        }

        return disciplinasMatriculadasDMDTO
    }
}