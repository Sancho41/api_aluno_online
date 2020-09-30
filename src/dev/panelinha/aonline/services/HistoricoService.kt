package dev.panelinha.dev.panelinha.aonline.services

import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dao.historico.DisciplinasCursadasDAO
import dev.panelinha.dev.panelinha.aonline.dao.historico.DisciplinasMatriculadasDAO
import dev.panelinha.dev.panelinha.aonline.dao.historico.DisciplinasPendentesDAO

class HistoricoService {
    fun disciplinasCursadas(user: User): List<Map<String, String>> {
        val dao = DisciplinasCursadasDAO(user)
        return dao.getTable()
    }

    fun disciplinasMatriculadas(user: User): List<Map<String, String>> {
        val dao = DisciplinasMatriculadasDAO(user)
        return dao.getTable()
    }

    fun disciplinasPendentes(user: User): List<Map<String, String>> {
        val dao = DisciplinasPendentesDAO(user)
        return dao.getTable()
    }
}
