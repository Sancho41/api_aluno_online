package dev.panelinha.aonline.services

import dev.panelinha.aonline.dao.AcademicoDAO

class AcademicoService {
    private val dao = AcademicoDAO()

    fun boletim (): List<Map<String, String>> {
        return dao.boletim()
    }
}
