package dev.panelinha.dev.panelinha.aonline.services

import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.crawler.DisciplinasCursadasCrawler
import dev.panelinha.dev.panelinha.aonline.crawler.DisciplinasMatriculadasCrawler
import dev.panelinha.dev.panelinha.aonline.crawler.DisciplinasPendentesCrawler
import dev.panelinha.dev.panelinha.aonline.dtos.DisciplinasCursadasDTO
import dev.panelinha.dev.panelinha.aonline.dtos.DisciplinasMatriculadasDTO
import dev.panelinha.dev.panelinha.aonline.dtos.DisciplinasPendentesDTO

class HistoricoService {
    fun disciplinasCursadas(user: User): DisciplinasCursadasDTO {
        return DisciplinasCursadasCrawler(user).scrap()
    }

    fun disciplinasMatriculadas(user: User): DisciplinasMatriculadasDTO {
        return DisciplinasMatriculadasCrawler(user).scrap()
    }

    fun disciplinasPendentes(user: User): DisciplinasPendentesDTO {
        return DisciplinasPendentesCrawler(user).scrap()
    }
}
