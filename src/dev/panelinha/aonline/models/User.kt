package dev.panelinha.aonline.models

import dev.panelinha.dev.panelinha.aonline.dtos.RegisterDTO
import io.ktor.auth.Principal
import org.mindrot.jbcrypt.BCrypt

class User(registerDTO: RegisterDTO) : Principal {
    var login: String = registerDTO.login
    private val senha: String = registerDTO.senha
    var isAdmin: Boolean = false
    var matricula: String? = null
    var senhaAO: String? = null

    init {
        if (registerDTO.matricula != null) this.matricula = registerDTO.matricula
        if (registerDTO.senhaAO != null) this.senhaAO = registerDTO.senhaAO
    }

    fun vericaSenha(senha: String): Boolean {
        return BCrypt.checkpw(senha, this.senha)
    }

    fun getSenha(): String {
        return BCrypt.hashpw(this.senha, BCrypt.gensalt())
    }
}
