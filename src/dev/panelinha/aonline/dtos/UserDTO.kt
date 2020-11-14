package dev.panelinha.aonline.dtos

import dev.panelinha.aonline.models.User

class UserDTO(user: User) {
    val email: String = user.email
    val matricula: String? = user.credenciaisAO?.matricula
}
