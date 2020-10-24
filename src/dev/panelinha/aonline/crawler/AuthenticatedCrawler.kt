package dev.panelinha.dev.panelinha.aonline.crawler

import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.exceptions.InvalidCredentialsAlunoOnlineException
import org.jsoup.Connection
import org.jsoup.Jsoup

open class AuthenticatedCrawler(val user: User) : ICrawler {
    private val authResourceUri = "http://online.iesb.br/aonline/middle_logon.asp"
    private val cookies: Map<String, String> = this.getCookies()

    companion object {
        fun checkCredentials(matricula: String, senha: String): Boolean {
            val user = User()
            user.credenciaisAO = User.CredenciaisAO(matricula, senha, "").criptografarSenha()
            val auth = AuthenticatedCrawler(user)
            return try {
                auth.getCookies()
                true
            } catch (e: InvalidCredentialsAlunoOnlineException) {
                false
            }
        }
    }

    private fun getCookies(): Map<String, String> {
        val matricula = user.credenciaisAO?.matricula
        val senha = user.credenciaisAO?.senhaDescriptografada()
        val login = Jsoup.connect(this.authResourceUri)
            .data("txtnumero_matricula", matricula)
            .data("txtsenha_tac", senha)
            .method(Connection.Method.POST)
            .execute()

        val regex = """(self.location.href = "logon.asp";)""".toRegex()

        if (regex.containsMatchIn(login.body()))
            throw InvalidCredentialsAlunoOnlineException("Login failed.")

        return login.cookies()
    }

    override fun crawler(url: String): Connection = Jsoup.connect(url).cookies(cookies)
}
