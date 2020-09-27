package dev.panelinha.dev.panelinha.aonline.dao

import com.google.gson.GsonBuilder
import dev.panelinha.aonline.dao.AuthDAO
import dev.panelinha.aonline.models.User
import org.jsoup.Jsoup

class FinanceiroDAO: PageDAO() {
    fun extratoFin(user: User): List<Any>{

        val extrato = getConnection("http://online.iesb.br/aonline/extrato_financeiro.asp", user)
                .data("dataIni", "01/01/2019")
                .data("dataFim", "26/01/2020")
                .post()

        return listOf()
        val headers = extrato
                .select("#ctnTabPagina2 > table > tbody > tr > td")
                .map{
                    if (it.text() != "")
                    it.text()
                    else "actions"
                }

        val rows = extrato.select("#ctnTabPagina2 > table > tbody > tr")

        return rows.map {
            val requerimentos: MutableMap<String, String> = mutableMapOf()
            val row = it.select("td")
            for((index, head) in headers.withIndex())
                requerimentos[head] = row[index].text()
            requerimentos

        }
    }
}