package dev.panelinha.aonline.models

class User(
    var nome: String,
    var matricula: String,
    var curso: String,
    var turno: String,
    var sitacao: String
) {
    private lateinit var senha: String
}
