package dev.panelinha.aonline.models

import dev.panelinha.dev.panelinha.aonline.dtos.RegisterDTO
import dev.panelinha.dev.panelinha.aonline.dtos.UpdateUserDTO
import io.ktor.auth.Principal
import org.mindrot.jbcrypt.BCrypt

class User(registerDTO: RegisterDTO) : Principal {
    var login: String = registerDTO.login
    private var senha: String = registerDTO.senha
    var isAdmin: Boolean = false
    var matricula: String? = null
    var senhaAO: String? = null

    init {
        if (registerDTO.matricula != null) this.matricula = registerDTO.matricula
        if (registerDTO.senhaAO != null) this.senhaAO = registerDTO.senhaAO
    }

    fun vericaSenha(senha: String): Boolean = BCrypt.checkpw(senha, this.senha)
    fun getSenha(): String = BCrypt.hashpw(this.senha, BCrypt.gensalt())

    fun updateUser(updateUserDTO: UpdateUserDTO){
        if (!updateUserDTO.novaSenha.isNullOrBlank()) {
            if (updateUserDTO.senhaAtual == null)
                throw Exception("Senha atual inválida.")
            else if (this.vericaSenha(updateUserDTO.senhaAtual!!)) {
                this.senha = updateUserDTO.novaSenha!!
            }
        }
        this.matricula = updateUserDTO.matricula ?: this.matricula
        this.senhaAO = updateUserDTO.senhaAO?: this.senhaAO
    }

    fun getCredenciais(): List<String> = listOf(matricula!!, senhaAO!!)
}
