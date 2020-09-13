package dev.panelinha.aonline.dao

import org.jsoup.Jsoup

class AcademicoDAO {
    fun boletim(): List<Map<String, String>> {
        val auth = AuthDAO()
        val loginCookies = auth.login(" ", " ")

        val boletim = Jsoup.connect("http://online.iesb.br/aonline/notas_freq_boletim_iframe.asp")
            .cookies(loginCookies)
            .get()

        val headers = boletim
            .select("#Open_Text_General > thead > tr:nth-child(2) > th")
            .map { it.text() }

        val rows = boletim
            .select("#Open_Text_General > tbody > tr")

        return rows.map {
            val materies: MutableMap<String, String> = mutableMapOf()
            val row = it.select("td")
            for ((index, head) in headers.withIndex())
                materies[head] = row[index].text()
            materies
        }
    }
}
