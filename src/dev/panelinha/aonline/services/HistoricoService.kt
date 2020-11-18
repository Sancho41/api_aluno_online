package dev.panelinha.aonline.services

import dev.panelinha.aonline.crawler.DisciplinasForadaGradeCrawler
import dev.panelinha.aonline.dtos.DisciplinasForadaGradeDTO
import dev.panelinha.aonline.models.User
import dev.panelinha.aonline.crawler.AtividadesComplementaresCrawler
import dev.panelinha.aonline.crawler.DisciplinasCursadasCrawler
import dev.panelinha.aonline.crawler.DisciplinasMatriculadasCrawler
import dev.panelinha.aonline.crawler.DisciplinasPendentesCrawler
import dev.panelinha.aonline.dtos.AtividadesComplementaresDTO
import dev.panelinha.aonline.dtos.DisciplinasCursadasDTO
import dev.panelinha.aonline.dtos.DisciplinasMatriculadasDTO
import dev.panelinha.aonline.dtos.DisciplinasPendentesDTO

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

    fun atividadesComplementares(user: User): AtividadesComplementaresDTO {
        return AtividadesComplementaresCrawler(user).scrap()
    }

    fun disciplinasForadaGrade(user: User): DisciplinasForadaGradeDTO {
        return DisciplinasForadaGradeCrawler(user).scrap()
    }
}
