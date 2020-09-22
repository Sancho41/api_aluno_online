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
    val cookies = dao.getCookies("1712130049", " ")
}
