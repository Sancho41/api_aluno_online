package dev.panelinha.dev.panelinha.aonline

import com.google.gson.GsonBuilder
import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.crawler.AgendamentoCrawler
import dev.panelinha.dev.panelinha.aonline.dtos.AgendamentoDTO

fun main() {
    val user = User()
    user.credenciaisAO = User.CredenciaisAO("1912130015", "27062001");
    user.credenciaisAO?.criptografarSenha()
    val crawler = AgendamentoCrawler(user)
    val retorno = crawler.scrap(AgendamentoDTO())
    val gson = GsonBuilder().setPrettyPrinting().create()
    println(gson.toJson(retorno))

//    println(UUID.randomUUID())

    // Key to string
//    val secret = KeyGenerator.getInstance("AES").generateKey()
//    val encoded = Base64.getEncoder().encodeToString(secret.encoded)


    // String to key
//    val decodedKey = Base64.getDecoder().decode(encoded)
//    val originalKey = SecretKeySpec(decodedKey, 0, decodedKey.size, "AES")


//    val secret = "sLt2u16nXhXYSBfn4Dka7g=="

//    val encrypted = AES.setKey(secret).encrypt("Gabriel Sancho")
//    println(encrypted)

//    val decrypted = AES.setKey(secret).decrypt("iF9p74oSkdIR4XyMW3fGKg==")
//    println(decrypted)
}
