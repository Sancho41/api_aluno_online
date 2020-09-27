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
    val rows = extrato.select("#ctnTabPagina2 > table > tbody > tr > td > table > tbody > tr")
    val informacoes = rows.filter{!it.select("td")[0].hasAttr("colspan")}.map{it.text()}

    println(informacoes)



}
