package dev.panelinha.dev.panelinha.aonline

import com.google.gson.GsonBuilder
import dev.panelinha.aonline.dao.AcademicoDAO
import dev.panelinha.aonline.dao.AuthDAO
import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dtos.RegisterDTO
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection
import java.nio.charset.Charset

fun main() {
// matricula
// senha

// nome
// curso
// turno
// sitacao

    val cookies = Jsoup.connect("http://online.iesb.br/aonline/middle_logon.asp")
        .data("txtnumero_matricula", "1712130049")
        .data("txtsenha_tac", "147368")
        .method(Connection.Method.POST)
        .execute()
        .cookies()

    val boletim = Jsoup
        .connect("http://online.iesb.br/aonline/notas_freq_boletim_iframe.asp")
        .cookies(cookies)
        .get()

    boletim.outputSettings().charset("windows-1252")
//    val doc = Jsoup.parse(boletim.toString(), "ISO-8859-1")

//    println(boletim.charset())

//    val registerDTO = RegisterDTO("sancho", "123345", "1712130049", "147368")
//    val user = User(registerDTO)
//
//    val dao = AcademicoDAO()
//    val boletim = dao.boletim(user)
//    boletim.disciplinas.forEach {
//        println(String(it.nome.toByteArray(Charset.forName("ISO-8859-1")), Charset.forName("ISO-8859-1")))
//    }
}

