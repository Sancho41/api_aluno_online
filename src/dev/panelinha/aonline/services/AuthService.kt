package dev.panelinha.aonline.services

import dev.panelinha.aonline.dao.AuthDAO
import dev.panelinha.aonline.models.User
import dev.panelinha.aonline.crawler.AuthenticatedCrawler
import dev.panelinha.aonline.dtos.*
import dev.panelinha.aonline.exceptions.InvalidCredentialsAlunoOnlineException
import dev.panelinha.aonline.utils.KeyGenerator

class AuthService {
    private val dao = AuthDAO()
    fun login(loginUserDto: LoginUserDTO): User {
        return dao.login(loginUserDto)
    }

    fun register(registerDTO: RegisterDTO): User {
        return dao.register(registerDTO)
    }

    fun registerAlunoOnline(user: User, registerAODTO: RegisterAODTO): ApiKeyDTO {
        val check = AuthenticatedCrawler.checkCredentials(registerAODTO.matricula, registerAODTO.senha)
        if (check) {
            val chave = KeyGenerator.getAESKey();
            user.credenciaisAO = User.CredenciaisAO(registerAODTO.matricula, registerAODTO.senha, chave)
            dao.updateAo(user)
            return ApiKeyDTO(chave)
        }
        throw InvalidCredentialsAlunoOnlineException("Credenciais do Aluno Online invalida.")
    }
}