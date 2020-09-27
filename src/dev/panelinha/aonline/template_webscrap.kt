package dev.panelinha.dev.panelinha.aonline

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
    val cookies = dao.getCookies("1912130015", "27062001")

    val extrato = Jsoup.connect("http://online.iesb.br/aonline/historico.asp")
        .cookies(cookies)
        .get()

    val rows = extrato.select("#ctnTabPagina2 > table > tbody > tr > td > b > b > table > tbody > tr")

    val info = rows.map{ it.text() }

   var i : Int = 0

    println("\n")

    info.forEach{
        println(info[i])
        i = i + 1
    }




}






