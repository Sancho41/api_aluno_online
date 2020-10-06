package dev.panelinha.aonline.services

import dev.panelinha.aonline.dao.AcademicoDAO
import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dtos.BoletimDTO

class AcademicoService {
    private val dao = AcademicoDAO()

    fun boletim (user: User): BoletimDTO {
        return dao.boletim(user)
    }

    fun horaAulas(user: User): Map<String, List<Map<String, String>>> {
        return dao.horaAulas(user)
    }
}
