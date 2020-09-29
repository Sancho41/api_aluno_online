package dev.panelinha.dev.panelinha.aonline

import com.google.gson.GsonBuilder
import dev.panelinha.aonline.dao.AuthDAO
import dev.panelinha.aonline.models.User
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

fun main () {
// matricula
// senha

// nome
// curso
// turno
// sitacao

    val dao = AuthDAO()
    val cookies = dao.getCookies("1912130054", "05092000")

    val historico = Jsoup.connect("http://online.iesb.br/aonline/historico.asp")
        .cookies(cookies)
        .get()

    val rows = historico.select("#ctnTabPagina2 > table > tbody > tr > td > b > b > b > b > b > b > table > tbody > tr")

    val headers = rows[0].select("td").map { it.text() }

    val materias = mutableListOf<Map<String, String>>()

    for(i in 1 until (rows.size - 1)){

        val row = rows[i].select("td")

        if(row.size == headers.size){
            val materia = mutableMapOf<String, String>()

            for((index, valor) in headers.withIndex()){
                materia[valor] = row[index].text()
            }

            materias.add(materia)
        }
    }

 val gson = GsonBuilder().setPrettyPrinting().create()
    val json = gson.toJson(materias)

println(json)


}

