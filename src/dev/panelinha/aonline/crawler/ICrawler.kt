package dev.panelinha.dev.panelinha.aonline.crawler

import org.jsoup.Connection
import org.jsoup.Jsoup

interface ICrawler {
    fun crawler(url: String): Connection? = Jsoup.connect(url)
    fun scrap(): Any? = null
}
