package dev.panelinha.dev.panelinha.aonline.crawler

import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dtos.HorarioDeAulasDTO

class HorarioDeAulasCrawler(user: User) : AuthenticatedCrawler(user) {
    override fun scrap(): HorarioDeAulasDTO {
        val crawler = crawler("http://online.iesb.br/aonline/horario.asp")
        val documento = crawler.get()

        val linhas = documento.select("#ctnTabPagina2 > table > tbody > tr > td > table:nth-child(4) > tbody > tr")

        val horarioDeAulasDTO = HorarioDeAulasDTO()

        var lastDay = linhas[1].text()
        for (i in 2 until linhas.size) {

            val values = linhas[i].select("td")
            if (values.size == 1)
                lastDay = linhas[i].text()
            else {
                horarioDeAulasDTO.addMateria(lastDay, values.map { it.text() })
            }
        }

        return horarioDeAulasDTO
    }
}
