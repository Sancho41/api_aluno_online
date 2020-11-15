package dev.panelinha.aonline.models

import dev.panelinha.aonline.dtos.RegisterDTO
import dev.panelinha.aonline.utils.AES
import io.ktor.auth.Principal
import io.ktor.utils.io.core.toByteArray
import org.mindrot.jbcrypt.BCrypt
import java.nio.charset.Charset
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class User : Principal {
    var email: String
    private var senha: String

    class CredenciaisAO(
        var matricula: String,
        private var senha: String,
        var chave: String = "nokey"
    ) {
        fun getSenha(): String = AES.setKey(this.chave).encrypt(this.senha)!!
        fun criptografarSenha() : CredenciaisAO {
            this.senha = AES.setKey(this.chave).encrypt(this.senha)!!
            return this
        }

        fun senhaDescriptografada(): String {
            return AES.setKey(this.chave).decrypt(this.senha)!!
        }

        fun atualizaSenha(novaSenha: String) {
            this.senha = novaSenha
            this.criptografarSenha()
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
