package dev.panelinha.dev.panelinha.aonline.dao

import dev.panelinha.aonline.dao.AuthDAO
import dev.panelinha.aonline.models.User
import org.jsoup.Jsoup

class ServicoDAO {

    fun agendamentos(user: User): List<Map<String, String>>{

        val auth = AuthDAO()
        val loginCookies = auth.getCookies(user)

        val atendimento = Jsoup.connect("http://apps.iesb.br/atendimentoagendado/atendimento/newatendimento")
                .cookies(loginCookies)
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