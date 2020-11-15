package dev.panelinha.aonline.dtos

class ExceptionDTO {

    var message: String

    constructor(mensagem: String){
        this.message = mensagem
    }

    constructor(error: Exception){
        this.message = error.message ?: "Ocorreu um erro."
    }
}