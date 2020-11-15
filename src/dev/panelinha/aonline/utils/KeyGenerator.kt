package dev.panelinha.aonline.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.KeyGenerator

class KeyGenerator {
    companion object {
        //TODO: Adicionar chave a partir de variavel de ambiente
        fun getAESKey(): String {
            val secret = KeyGenerator.getInstance("AES").generateKey()
            return Base64.getEncoder().encodeToString(secret.encoded)
        }
    }
}
