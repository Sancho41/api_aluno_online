package dev.panelinha.dev.panelinha.aonline.crawler

import dev.panelinha.dev.panelinha.aonline.dtos.ExtratoDTO
import dev.panelinha.dev.panelinha.aonline.dtos.ServicoDTO
import org.jsoup.Connection
import org.jsoup.Jsoup

interface ICrawler {
    fun crawler(url: String): Connection? = Jsoup.connect(url)
    fun scrap(data: ExtratoDTO): Any? = null
    fun scrap(data: ServicoDTO): Any? = null
    fun scrap(): Any? = null
}
