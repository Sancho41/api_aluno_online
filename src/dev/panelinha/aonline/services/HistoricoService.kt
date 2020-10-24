package dev.panelinha.dev.panelinha.aonline.services

import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.crawler.DisciplinasCursadasCrawler
import dev.panelinha.dev.panelinha.aonline.dao.historico.DisciplinasMatriculadasDAO
import dev.panelinha.dev.panelinha.aonline.dao.historico.DisciplinasPendentesDAO
import dev.panelinha.dev.panelinha.aonline.dtos.DisciplinasCursadasDTO

class HistoricoService {
    fun disciplinasCursadas(user: User): DisciplinasCursadasDTO {
        val dao = DisciplinasCursadasCrawler(user)
        return dao.scrap()
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
