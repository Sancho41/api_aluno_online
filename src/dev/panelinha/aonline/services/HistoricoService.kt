package dev.panelinha.dev.panelinha.aonline.services

import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dao.historico.DisciplinasCursadasDAO
import dev.panelinha.dev.panelinha.aonline.dao.historico.DisciplinasMatriculasdasDAO

class HistoricoService {
    fun disciplinasCursadas(user: User): List<Map<String, String>> {
        val dao = DisciplinasCursadasDAO(user)
        return dao.getTable()
    }

    fun disciplinasMatriculadas(user: User): List<Map<String, String>> {
        val dao = DisciplinasMatriculasdasDAO(user)
        return dao.getTable()
    }
}
