package dev.panelinha.aonline.dao

import com.google.gson.GsonBuilder
import dev.panelinha.aonline.models.User
import org.jsoup.Jsoup

class AcademicoDAO {
    fun boletim(user: User): List<Map<String, String>> {
        val auth = AuthDAO()
        val loginCookies = auth.getCookies(user.matricula, user.senha)

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

    fun atvCompl(user: User)  {
        val auth = AuthDAO()
        val loginCookies = auth.getCookies(user.matricula, user.senha)

        val atividades = Jsoup.connect("http://apps.iesb.br/sistemasAcademicos/application/modules/aonline/views/atividadesComplementares/js/atividades-complementares.js")
                .cookies(loginCookies)
                .get()

        //fazer adaptação para pegar o data e trazer informações das atividades
    }

    fun horaAulas(user: User) : Map<String, List<Map<String, String>>> {
        val auth = AuthDAO()
        val loginCookies = auth.getCookies(user.matricula, user.senha)

        val horario = Jsoup.connect("http://online.iesb.br/aonline/horario.asp")
                .cookies(loginCookies)
                .get()

        val rows = horario.select("#ctnTabPagina2 > table > tbody > tr > td > table:nth-child(4) > tbody > tr")

        val headers = rows[0].select("td").map { it.text() }

        val horarios: MutableMap<String, MutableList<MutableMap<String, String>>> = mutableMapOf()

        var lastDay = rows[1].text()
        for (i in 2 until rows.size) {

            val values = rows[i].select("td")
            if (values.size == 1)
                lastDay = rows[i].text()
            else {
                val materia: MutableMap<String, String> = mutableMapOf()
                for ((index, head) in headers.withIndex()) {
                    materia[head] = values[index].text()
                }

                if (horarios[lastDay] == null)
                    horarios[lastDay] = arrayListOf()
                horarios[lastDay]?.add(materia)
            }
        }

        return horarios
    }
}
