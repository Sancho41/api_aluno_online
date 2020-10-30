package dev.panelinha.aonline.dao

import com.mongodb.MongoWriteException
import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dao.DAO
import dev.panelinha.dev.panelinha.aonline.dtos.LoginUserDTO
import dev.panelinha.dev.panelinha.aonline.dtos.RegisterDTO
import dev.panelinha.dev.panelinha.aonline.exceptions.InvalidCredentialsAlunoOnlineException
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
            throw InvalidCredentialsAlunoOnlineException("Login failed.")

        return login.cookies()
    }

    fun getCookies(user: User): Map<String, String> { //TODO: Remover o scraping desse arquivo
        val matricula = user.credenciaisAO?.matricula;
        val senha = user.credenciaisAO?.senhaDescriptografada()
        return this.getCookies(matricula!!, senha!!)
    }

    fun login(loginUserDTO: LoginUserDTO): User {
        val collection = getCollection<User>()
        val user = collection.findOne { User::email eq loginUserDTO.email }

        if (!user!!.verificaSenha(loginUserDTO.senha))
            throw InvalidCredentialsAlunoOnlineException("Login failed.")

        return user
    }

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

    fun getUserByEmail(email: String, chave: String? = "nokey"): Principal {
        val collection = getCollection<User>()

        val user = collection.findOne(User::email eq email)
            ?: throw Exception("Não foi possível efetuar login")

        user.credenciaisAO?.chave = chave;
        return user;
    }

    fun update(user: User): User {
        val collection = getCollection<User>()
        collection.updateOne(User::email eq user.email, user)
        return this.getUserByEmail(user.email) as User
    }

    fun updateAo(user: User): User {
        val collection = getCollection<User>()

        val matricula = user.credenciaisAO?.matricula!!
        val senha = user.credenciaisAO?.getSenha()!!

        val target = "{\$set: { credenciaisAO: { matricula: \"$matricula\", senha: \"$senha\" } } }"

        collection.updateOne(user::email eq user.email, target)
        return user;
    }
}
