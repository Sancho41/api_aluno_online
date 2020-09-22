package dev.panelinha.dev.panelinha.aonline

import dev.panelinha.aonline.dao.AuthDAO
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
    val cookies = dao.getCookies("1712130049", "300898")

    val extrato = Jsoup.connect("http://online.iesb.br/aonline/extrato_financeiro.asp")
        .cookies(cookies)
        .data("dt_ini", "01/11/2019")
        .data("dt_fim", "01/11/2020")
        .post()

    println(extrato.body())
}
