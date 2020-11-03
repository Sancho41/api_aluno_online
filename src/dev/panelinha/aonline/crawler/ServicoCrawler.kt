package dev.panelinha.dev.panelinha.aonline.crawler

import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dtos.ServicoDTO

class ServicoCrawler(user: User): AuthenticatedCrawler(user) {
    override fun scrap(data: ServicoDTO): Any? {
        val crawler = this.crawler("http://apps.iesb.br/atendimentoagendado/atendimento/newatendimento")
        val document = crawler
            .data("tipo", "aluno")
            .data("categoria", "")
            .data("codigo", "UU8wQURNekVqTXhjVE0")
            .post()

        val servicoDTO = ServicoDTO()

        //println(document)

        document.select(
            ".classdatatable > tbody > tr"
        ).forEach { elemento ->
            val linha = elemento.select("td").map{ coluna -> coluna.text() }

            servicoDTO.addAgendamentos(linha)

        }

        return servicoDTO
    }
}