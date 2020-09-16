package dev.panelinha.dev.panelinha.aonline.dtos

class RegisterUserDTO(var matricula: String, var senha: String) {
    override fun toString(): String {
        return "matricula: $matricula; senha: $senha;"
    }
}
