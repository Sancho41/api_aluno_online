package dev.panelinha.dev.panelinha.aonline.dao

import dev.panelinha.aonline.dao.AuthDAO
import dev.panelinha.aonline.models.User
import org.jsoup.Jsoup

class ServicoDAO: PageDAO() {
    fun agendamentos(user: User): List<Map<String, String>>{

        val atendimento = getConnection("http://apps.iesb.br/atendimentoagendado/atendimento/newatendimento", user)
                .data("tipo", "aluno")
                .data("categoria", "")
                .data("codigo", "UU8wQURNekVqTXhjVE0")
                .post()

        val headers = atendimento
            .select("#home .classdatatable > thead > tr > th")
            .map{
                if (it.text() != "")
                    it.text()
                else "actions"
            }

        val rows = atendimento.select("#home .classdatatable > tbody > tr")

        return rows.map {
            val requerimentos: MutableMap<String, String> = mutableMapOf()
            val row = it.select("td")
            for((index, head) in headers.withIndex())
                requerimentos[head] = row[index].text()
            requerimentos

        }
    }
}