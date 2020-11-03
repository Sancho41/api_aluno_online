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

        document.select(
            "#home .classdatatable > thead > tr > th"
        ).forEach { elemento ->
            val linha = elemento.select("th").map{ coluna -> coluna.text() }
            if (linha.size == 4 )
                servicoDTO.addAgendamentos(linha)

        }

        return servicoDTO
    }
}