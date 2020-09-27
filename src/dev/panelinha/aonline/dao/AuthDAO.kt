package dev.panelinha.aonline.dao

import com.mongodb.MongoWriteException
import com.mongodb.client.model.UpdateOptions
import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dao.DAO
import dev.panelinha.dev.panelinha.aonline.dtos.LoginUserDTO
import dev.panelinha.dev.panelinha.aonline.dtos.RegisterDTO
import dev.panelinha.dev.panelinha.aonline.dtos.UpdateUserDTO
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.litote.kmongo.*
import io.ktor.auth.Principal

class AuthDAO: DAO() {

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
        return this.getCookies(user.matricula as String, user.senhaAO as String)
    }

    fun login(loginUserDTO: LoginUserDTO): User {
        val collection = getCollection<User>()
        val user = collection.findOne { User::login eq loginUserDTO.login }

        if (!user!!.vericaSenha(loginUserDTO.senha))
            throw Exception("Login failed.")

        return user
    }

//    private fun register(loginUserDTO: LoginUserDTO, cookies: Map<String, String>): User {
//
//        val info = Jsoup.connect("http://online.iesb.br/aonline/dados_do_aluno.asp")
//            .cookies(cookies)
//            .get()
//
//        val nome = info.select("#ctnTabPagina2 > table > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(2) > td.font01")
//            .text()
//
//        val cursoInfo = info.select("#ctnTabPagina2 > table > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(29) > td:nth-child(2)")
//            .text()
//        val cursoRegex = "(?:Curso: )(.+)".toRegex()
//        val (curso) = cursoRegex.find(cursoInfo)!!.destructured
//
//        val turnoInfo = info.select("#ctnTabPagina2 > table > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(29) > td:nth-child(3)")
//            .text()
//        val turnoRegex = "(?:Turno: )(.+)".toRegex()
//        val (turno) = turnoRegex.find(turnoInfo)!!.destructured
//
//        val situacaoInfo = info.select("#ctnTabPagina2 > table > tbody > tr > td > table:nth-child(1) > tbody > tr:nth-child(30) > td:nth-child(3)")
//            .text()
//        val situacaoRegex = "(?:Situação: )(.+)".toRegex()
//        val (situacao) = situacaoRegex.find(situacaoInfo)!!.destructured
//
//        val user = User(nome, loginUserDTO.matricula, curso, turno, situacao, loginUserDTO.senha)
//
//        try {
//            val col = db.getCollection<User>()
//            col.insertOne(user)
//            return user
//        } catch (e: MongoWriteException) {
//            if (e.code == 11000)
//                throw Exception("Usuário já registrado.")
//            throw e
//        }
//    }

    fun register(registerDTO: RegisterDTO): User {
        val user = User(registerDTO)
        try {
            val col = getCollection<User>()
            col.insertOne(user)
        } catch (e: MongoWriteException) {
            if (e.code == 11000)
                throw Exception("Usuário já registrado.")
            throw e
        }
        return user
    }

    fun getUserByLogin(login: String): Principal {
        val collection = getCollection<User>()
        return collection.findOne(User::login eq login)
            ?: throw Exception("Não foi possível efetuar login")
    }

    fun upToDate(user: User): Boolean {
        if (user.matricula == null || user.senhaAO == null)
            return false
        return try {
            getCookies(user)
            true
        } catch (e: Exception){
            false
        }
    }

    fun update(user: User, updateUserDTO: UpdateUserDTO): User {
        val collection = getCollection<User>()
        collection.updateOne(User::login eq user.login, updateUserDTO)
        return this.getUserByLogin(user.login) as User
    }
}
