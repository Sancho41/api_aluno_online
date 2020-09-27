package dev.panelinha.aonline.models

import dev.panelinha.dev.panelinha.aonline.dtos.RegisterDTO
import io.ktor.auth.Principal

class User: Principal {
    var login: String
    var senha: String
    var isAdmin: Boolean = false
    var matricula: String? = null
    var senhaAO: String? = null

    constructor(registerDTO: RegisterDTO) {
        this.login = registerDTO.login;
        this.senha = registerDTO.senha;
        if (registerDTO.matricula != null) this.matricula = registerDTO.matricula
        if (registerDTO.senhaAO != null) this.senhaAO = registerDTO.senhaAO
    }

    fun vericaSenha(senha: String): Boolean {
        return this.senha == senha
    }
}
