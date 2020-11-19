package dev.panelinha.aonline.crawler

import dev.panelinha.aonline.models.User
import dev.panelinha.aonline.dtos.AgendamentoDTO

class AgendamentoCrawler(user: User): AuthenticatedCrawler(user) {
    override fun scrap(): AgendamentoDTO {
        val crawlerCodigo = this.crawler("http://online.iesb.br/aonline/ie_agendamento_fies.asp").get()
        val regex = Regex("(?:codigo: ')([^']+)(?:')")
        val corpo = crawlerCodigo.body().toString()
        val find = regex.find(corpo)
        val codigo = find!!.groups[1]?.value
        println(codigo)


        val crawler = this.crawler("http://apps.iesb.br/atendimentoagendado/atendimento/newatendimento")
        val document = crawler
            .data("tipo", "aluno")
            .data("categoria", "")
            .data("codigo", codigo)
            .post()

        val agendamentoDTO = AgendamentoDTO()

        document.select(
            ".classdatatable > tbody > tr"
        ).forEach { elemento ->
            val linha = elemento.select("td").map{ coluna -> coluna.text() }

            agendamentoDTO.addAgendamentos(linha)

        }

        return agendamentoDTO
    }
}