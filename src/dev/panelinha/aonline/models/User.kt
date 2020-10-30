package dev.panelinha.aonline.models

import dev.panelinha.dev.panelinha.aonline.dtos.RegisterDTO
import io.ktor.auth.Principal
import io.ktor.utils.io.core.toByteArray
import org.mindrot.jbcrypt.BCrypt
import java.nio.charset.Charset
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class User : Principal {
    var email: String
    private var senha: String

    class CredenciaisAO(
        var matricula: String,
        private var senha: String,
        var chave: String? = "nokey"
    ) {
        init {
            this.criptografarSenha()
        }

        fun getSenha(): String = this.senha
        private fun criptografarSenha() {
            val encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE")
            val chave = SecretKeySpec(this.chave?.toByteArray(), "AES")
            encripta.init(
                Cipher.ENCRYPT_MODE,
                chave,
                IvParameterSpec("AAAAAAAAAAAAAAAA".toByteArray())
            )
            this.senha = String(encripta.doFinal(this.senha.toByteArray()))
        }

        fun senhaDescriptografada(): String {
            val decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE")
            val chave = SecretKeySpec(this.chave?.toByteArray(), "AES")
            decripta.init(
                Cipher.DECRYPT_MODE,
                chave,
                IvParameterSpec("AAAAAAAAAAAAAAAA".toByteArray())
            )
            return String(decripta.doFinal(this.chave?.toByteArray()))
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
