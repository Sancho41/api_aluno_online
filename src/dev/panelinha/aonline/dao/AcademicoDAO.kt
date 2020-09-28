package dev.panelinha.aonline.dao

import com.google.gson.GsonBuilder
import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dao.PageDAO
import org.jsoup.Connection
import org.jsoup.Jsoup

class AcademicoDAO: PageDAO() {
    fun boletim(user: User): List<Map<String, String>> {
        val boletim = getConnection("http://online.iesb.br/aonline/notas_freq_boletim_iframe.asp", user).get()

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

    fun atvCompl(user: User) {
        val atividades = getConnection(
                "https://apps.iesb.br/sistemasAcademicos/application/modules/aonline/views/atividadesComplementares/js/atividades-complementares.js",
                user
        ).get()

        //fazer adaptação para pegar o data e trazer informações das atividades
        //val atvDisponiveis = atividades
        //        .select("#nav nav-tabs list-unstyled components > li > a") nao sei se é melhor pegar a DATA ou pegar direto as colunas
    }

    fun horaAulas(user: User): Map<String, List<Map<String, String>>> {
        val horario = getConnection("http://online.iesb.br/aonline/horario.asp", user).get()

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

    fun historicoAcad(user: User): Map<String, String> {

        val historicoAcad = getConnection("http://online.iesb.br/aonline/historico.asp", user).get()

        val rows = historicoAcad.select("#ctnTabPagina2 > table > tbody > tr > td > table > tr")

        val info = rows.filter { !it.hasAttr("colspan") }.map { it.text() }

        val infoMap: MutableMap<String, String> = mutableMapOf<String, String>()

        info.forEach {
            val listChaveValor = it.split(":")
            if (listChaveValor.size >= 2) {
                val (chave, valor) = listChaveValor
                infoMap[chave] = valor
            }
        }

        return infoMap
    }

    fun historicoAcadDisciplinaCursada(user: User): List<Map<String, String>> {

        val historicoAcadDisciplinaCursada = getConnection("http://online.iesb.br/aonline/historico.asp", user).get()

        val rows = historicoAcadDisciplinaCursada.select("#ctnTabPagina2 > table > tbody > tr > td > b > b > table > tbody > tr")

        val headers = rows[0].select("td").map { it.text() }

        val materias = mutableListOf<Map<String, String>>()

        for (i in 1 until (rows.size - 1)) {

            val row = rows[i].select("td")

            if (row.size == headers.size) {
                val materia = mutableMapOf<String, String>()

                for ((index, valor) in headers.withIndex()) {
                    materia[valor] = row[index].text()
                }

                materias.add(materia)
            }
        }

        return(materias)

    }

}

