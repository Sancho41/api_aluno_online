package dev.panelinha.aonline.utils

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec


class AES (
    private var key: ByteArray,
    private var secret: String
) {

    private var secretKey: SecretKeySpec? = null



    companion object {
        fun setKey(myKey: String): AES {
            var sha: MessageDigest? = null
            val aes = AES(myKey.toByteArray(charset("UTF-8")), myKey)
            sha = MessageDigest.getInstance("SHA-1")
            aes.key = sha.digest(aes.key)
            aes.key = Arrays.copyOf(aes.key, 16)
            aes.secretKey = SecretKeySpec(aes.key, "AES")
            return aes;
        }

        fun generateKey(): String {
            val secret = KeyGenerator.getInstance("AES").generateKey()
            return Base64.getEncoder().encodeToString(secret.encoded)
        }
    }

    fun encrypt(strToEncrypt: String): String? {
        try {
            setKey(secret)
            val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            return Base64.getEncoder()
                .encodeToString(cipher.doFinal(strToEncrypt.toByteArray(charset("UTF-8"))))
        } catch (e: Exception) {
            println("Error while encrypting: $e")
        }
        return null
    }

    fun decrypt(strToDecrypt: String?): String? {
        try {
            setKey(secret)
            val cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
            cipher.init(Cipher.DECRYPT_MODE, secretKey)
            return String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)))
        } catch (e: Exception) {
            println("Error while decrypting: $e")
        }
        return null
    }
}
