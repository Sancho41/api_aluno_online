package dev.panelinha.dev.panelinha.aonline

import org.jsoup.Connection
import org.jsoup.Jsoup

fun main () {
    val login = Jsoup.connect("http://online.iesb.br/aonline/middle_logon.asp")
        .data("txtnumero_matricula", "1712130049")
        .data("txtsenha_tac", "MinhaSenha")
        .method(Connection.Method.POST)
        .execute()

    val authCookies = login.cookies()
}
