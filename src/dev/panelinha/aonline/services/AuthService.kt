package dev.panelinha.dev.panelinha.aonline.services

import dev.panelinha.aonline.dao.AuthDAO
import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dtos.LoginUserDTO

class AuthService {
    private val dao = AuthDAO()
    fun login(loginUserDto: LoginUserDTO): User {
        return dao.login(loginUserDto)
    }
}