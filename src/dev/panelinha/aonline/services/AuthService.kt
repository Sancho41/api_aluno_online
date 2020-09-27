package dev.panelinha.dev.panelinha.aonline.services

import dev.panelinha.aonline.dao.AuthDAO
import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dtos.LoginUserDTO
import dev.panelinha.dev.panelinha.aonline.dtos.RegisterDTO
import dev.panelinha.dev.panelinha.aonline.dtos.UpdateUserDTO

class AuthService {
    private val dao = AuthDAO()
    fun login(loginUserDto: LoginUserDTO): User {
        return dao.login(loginUserDto)
    }

    fun register(registerDTO: RegisterDTO): User {
        return dao.register(registerDTO)
    }

    fun upToDate(user: User): Boolean {
        return dao.upToDate(user)
    }

    fun update(user: User, updateUserDTO: UpdateUserDTO): User {
        return dao.update(user, updateUserDTO)
    }
}