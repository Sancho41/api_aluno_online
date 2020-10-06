package dev.panelinha.aonline.dao

import com.google.gson.GsonBuilder
import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dao.PageDAO
import dev.panelinha.dev.panelinha.aonline.dtos.BoletimDTO
import org.jsoup.Connection
import org.jsoup.Jsoup

class AcademicoDAO: PageDAO() {
    fun boletim(user: User): BoletimDTO {
        val boletim = getConnection("http://online.iesb.br/aonline/notas_freq_boletim_iframe.asp", user).get()

        val rows = boletim
            .select("#Open_Text_General > tbody > tr")
            .map {row ->
                row.select("td").map { it.text() }
            }

        return BoletimDTO(rows)
    }

    // TODO
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
}
