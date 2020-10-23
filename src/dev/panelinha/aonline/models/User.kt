package dev.panelinha.aonline.models

import dev.panelinha.dev.panelinha.aonline.dtos.RegisterDTO
import io.ktor.auth.Principal
import org.mindrot.jbcrypt.BCrypt

class User: Principal {
    var email: String
    private var senha: String

    class CredenciaisAO (
        var matricula: String,
        private var senha: String,
        private var chave: String = "nokey"
    ) {
        fun getSenha(): String = this.senha
        fun criptografarSenha(): CredenciaisAO = this
        
        
        fun senhaDescriptografada() = this.senha

        fun atualizaSenha(novaSenha: String) {
            this.senha = novaSenha
        }
    }

    constructor(registerDTO: RegisterDTO) {
        this.email = registerDTO.email
        this.senha = registerDTO.senha
    }

    constructor() {
        this.email = ""
        this.senha = ""
    }

    var credenciaisAO: CredenciaisAO? = null

    fun verificaSenha(senha: String): Boolean = BCrypt.checkpw(senha, this.senha)
    fun getSenha(): String = BCrypt.hashpw(this.senha, BCrypt.gensalt())
    fun atualizaSenha(senhaAntiga: String, novaSenha: String) {
        if (this.verificaSenha(senhaAntiga)) this.senha = novaSenha
    }
}
