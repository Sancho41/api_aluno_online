package dev.panelinha.dev.panelinha.aonline

import com.google.gson.GsonBuilder
import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.crawler.*
import dev.panelinha.dev.panelinha.aonline.dtos.DisciplinasCursadasDTO
import dev.panelinha.dev.panelinha.aonline.dtos.ExtratoDTO
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
    user.credenciaisAO = User.CredenciaisAO("1912130015", "27062001");
    val crawler = FinanceiroCrawler(user)
    val retorno = crawler.scrap(ExtratoDTO("10/01/2019","20/10/2020"))
    val gson = GsonBuilder().setPrettyPrinting().create()
    println(gson.toJson(retorno))
}

