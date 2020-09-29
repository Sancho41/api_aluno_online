package dev.panelinha.dev.panelinha.aonline.dao

import com.google.gson.GsonBuilder
import dev.panelinha.aonline.dao.AuthDAO
import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dtos.ExtratoDTO
import org.jsoup.Jsoup

class FinanceiroDAO: PageDAO() {
    fun extratoFin(user: User, extratoDTO: ExtratoDTO): List<Any>{

        val extrato = getConnection("http://online.iesb.br/aonline/extrato_financeiro.asp", user)
                .data("dt_ini", extratoDTO.dataIni)
                .data("dt_fim", extratoDTO.dataFim)
                .post()

        val table = extrato
                .select("#ctnTabPagina2 > table > tbody > tr > td > table:nth-child(6) > tbody > tr")

        val headers = table[1].select("td").map{ it.text() }

        val mensalidades = mutableListOf<Map<String, String>>()

        for (i:Int in 3 until table.size){
            val row = table[i].select("td")
            if(row.size != headers.size) break

            val newRow = mutableMapOf<String, String>()
            for ((index:Int, valor:String) in headers.withIndex()){
                newRow[valor] = row[index].text()
            }
            mensalidades.add(newRow)

        }

        return mensalidades
    }
}