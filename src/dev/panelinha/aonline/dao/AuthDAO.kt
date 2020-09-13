package dev.panelinha.aonline.dao

import org.jsoup.Connection
import org.jsoup.Jsoup

class AuthDAO {
    fun login(matricula: String, senha: String): Map<String, String> {
        val login = Jsoup.connect("http://online.iesb.br/aonline/middle_logon.asp")
            .data("txtnumero_matricula", matricula)
            .data("txtsenha_tac", senha)
            .method(Connection.Method.POST)
            .execute()

        return login.cookies()
    }
}
