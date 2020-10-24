package dev.panelinha.dev.panelinha.aonline

import com.google.gson.GsonBuilder
import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.crawler.DisciplinasCursadasCrawler
import dev.panelinha.dev.panelinha.aonline.crawler.HorarioDeAulasCrawler
import dev.panelinha.dev.panelinha.aonline.dtos.DisciplinasCursadasDTO
import dev.panelinha.dev.panelinha.aonline.dtos.RegisterDTO
import org.jsoup.Connection
import org.jsoup.Jsoup

fun main() {
// matricula
// senha

// nome
// curso
// turno
// sitacao

//    val cookies = Jsoup.connect("http://online.iesb.br/aonline/middle_logon.asp")
//        .data("txtnumero_matricula", "1712130049")
//        .data("txtsenha_tac", "147368")
//        .method(Connection.Method.POST)
//        .execute()
//        .cookies()

    val user = User()
    user.credenciaisAO = User.CredenciaisAO("1712130049", "147368");
    val crawler = DisciplinasCursadasCrawler(user)
    crawler.scrap()
    val gson = GsonBuilder().setPrettyPrinting().create()
    println(gson.toJson(crawler.scrap()))
}

