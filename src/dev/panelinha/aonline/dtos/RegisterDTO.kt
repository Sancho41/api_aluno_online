package dev.panelinha.dev.panelinha.aonline.dtos

class RegisterDTO(
    var login: String,
    var senha: String,
    var matricula: String? = null,
    var senhaAO: String? = null
)