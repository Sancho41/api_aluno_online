package dev.panelinha.aonline.dao

import com.mongodb.MongoWriteException
import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dao.DAO
import dev.panelinha.dev.panelinha.aonline.dtos.LoginUserDTO
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.litote.kmongo.*
import io.ktor.auth.Principal

class AuthDAO {
    private val db = DAO()

    fun getCookies(matricula: String, senha: String): Map<String, String> {
        val login = Jsoup.connect("http://online.iesb.br/aonline/middle_logon.asp")
            .data("txtnumero_matricula", matricula)
            .data("txtsenha_tac", senha)
            .method(Connection.Method.POST)
            .execute()

        val regex = """(self.location.href = "logon.asp";)""".toRegex()

        if (regex.containsMatchIn(login.body()))
            throw Exception("Login failed.")

        return login.cookies()
    }

    fun getCookies(user: User): Map<String, String> {
        return this.getCookies(user.matricula, user.senha)
    }

    fun login(loginUserDTO: LoginUserDTO): User {
        val cookies = this.getCookies(loginUserDTO.matricula, loginUserDTO.senha)

        val collection = db.getCollection<User>()

        return collection.findOne(User::matricula eq loginUserDTO.matricula)
            ?: register(loginUserDTO, cookies)
    }

    private fun register(loginUserDTO: LoginUserDTO, cookies: Map<String, String>): User {

        val info = Jsoup.connect("http://online.iesb.br/aonline/dados_do_aluno.asp")
            .cookies(cookies)
            .get()

        val nome = info.select("#ctnTabPagina2 > table > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(2) > td.font01")
            .text()

        val cursoInfo = info.select("#ctnTabPagina2 > table > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(29) > td:nth-child(2)")
            .text()
        val cursoRegex = "(?:Curso: )(.+)".toRegex()
        val (curso) = cursoRegex.find(cursoInfo)!!.destructured

        val turnoInfo = info.select("#ctnTabPagina2 > table > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(29) > td:nth-child(3)")
            .text()
        val turnoRegex = "(?:Turno: )(.+)".toRegex()
        val (turno) = turnoRegex.find(turnoInfo)!!.destructured

        val situacaoInfo = info.select("#ctnTabPagina2 > table > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(30) > td:nth-child(3)")
            .text()
        val situacaoRegex = "(?:Situação: )(.+)".toRegex()
        val (situacao) = situacaoRegex.find(situacaoInfo)!!.destructured

        val user = User(nome, loginUserDTO.matricula, curso, turno, situacao, loginUserDTO.senha)

        try {
            val col = db.getCollection<User>()
            col.insertOne(user)
            return user
        } catch (e: MongoWriteException) {
            if (e.code == 11000)
                throw Exception("Usuário já registrado.")
            throw e
        }
    }

    fun loginByMatricula(matricula: String): Principal {
        val collection = db.getCollection<User>()
        return collection.findOne(User::matricula eq matricula)
            ?: throw Exception("Não foi possível efetuar login")
    }
}
