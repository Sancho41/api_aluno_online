package dev.panelinha.aonline.crawler

import dev.panelinha.aonline.dtos.DTO

interface IScraper : ICrawler {
    fun scrap(): DTO
}