package dev.panelinha.dev.panelinha.aonline.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.crypto.KeyGenerator

class KeyGenerator {
    companion object {
        //TODO: Adicionar chave a partir de variavel de ambiente
        fun getAESKey(input: String = "MyEnvKey"): String {
            val keygen = KeyGenerator.getInstance("AES")
            keygen.init(128)
            return keygen.generateKey().toString()
        }
    }
}
