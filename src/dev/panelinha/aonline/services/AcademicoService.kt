package dev.panelinha.aonline.services

import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.crawler.BoletimCrawler
import dev.panelinha.dev.panelinha.aonline.crawler.HorarioDeAulasCrawler
import dev.panelinha.dev.panelinha.aonline.dtos.BoletimDTO
import dev.panelinha.dev.panelinha.aonline.dtos.HorarioDeAulasDTO

class AcademicoService {
    fun boletim (user: User): BoletimDTO {
        return BoletimCrawler(user).scrap()
    }

    fun horaAulas(user: User): HorarioDeAulasDTO {
        return HorarioDeAulasCrawler(user).scrap()
    }
}
