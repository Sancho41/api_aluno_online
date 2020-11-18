package dev.panelinha.aonline.crawler

import dev.panelinha.aonline.models.User
import dev.panelinha.aonline.dtos.BoletimDTO

class BoletimCrawler(user: User) : AuthenticatedCrawler(user) {
    override val data: Map<String, String> = mapOf()
    override fun scrap(): BoletimDTO {
        val crawler = crawler("http://online.iesb.br/aonline/notas_freq_boletim_iframe.asp")
        val boletim = crawler
            .get()
            .select("#Open_Text_General > tbody > tr")
            .map { row -> row.select("td").map { it.text() } }
        return BoletimDTO(boletim)
    }
}
