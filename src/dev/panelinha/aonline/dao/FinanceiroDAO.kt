package dev.panelinha.dev.panelinha.aonline.dao

import com.google.gson.GsonBuilder
import dev.panelinha.aonline.dao.AuthDAO
import dev.panelinha.aonline.models.User
import org.jsoup.Jsoup

class FinanceiroDAO {
    fun extratoFin(user: User): List<>{
        val auth = AuthDAO()
        val loginCookies = auth.getCookies(user.matricula, user.senha)

        val extrato = Jsoup.connect("http://online.iesb.br/aonline/extrato_financeiro.asp")
                .cookies(loginCookies)
                .data("dataIni", "01/01/2019")
                .data("dataFim", "26/01/2020")
                .post()

        //val headers = adaptação para extrato
    }
}