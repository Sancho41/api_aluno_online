package dev.panelinha.aonline.models

import io.ktor.auth.Principal

class User(
    var nome: String,
    var matricula: String,
    var curso: String,
    var turno: String,
    var sitacao: String,
    var senha: String
): Principal {
    override fun toString(): String {
        return """
            nome: $nome,
            matricula: $matricula,
            curso: $curso,
            turno: $turno,
            situacao: $sitacao,
        """.trimIndent()
    }
}
