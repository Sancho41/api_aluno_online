package dev.panelinha.aonline.crawler

import dev.panelinha.aonline.dtos.DTO
import org.jsoup.Connection
import org.jsoup.Jsoup

interface ICrawler {
    fun crawler(url: String): Connection? = Jsoup.connect(url)
    fun scrap(): DTO
}
