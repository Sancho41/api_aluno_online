package dev.panelinha.dev.panelinha.aonline.dao

import dev.panelinha.aonline.dao.AuthDAO
import dev.panelinha.aonline.models.User
import org.jsoup.Connection
import org.jsoup.Jsoup

open class PageDAO {
    var loginCookie: Map<String, String> = mapOf()
    var authDAO: AuthDAO? = null

    private fun getCookie(user: User): Map<String, String> {
        if (authDAO == null)
            authDAO = AuthDAO()

        if (loginCookie.isEmpty())
            loginCookie = authDAO!!.getCookies(user)


        return loginCookie
    }

    fun getConnection(url: String, user: User): Connection {
        return Jsoup.connect(url).cookies(getCookie(user))
    }

    fun getConnection(url: String): Connection {
        return Jsoup.connect(url);
    }
}
