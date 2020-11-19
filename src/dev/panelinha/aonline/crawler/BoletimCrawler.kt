package dev.panelinha.aonline.crawler

import dev.panelinha.aonline.models.User
import dev.panelinha.aonline.dtos.BoletimDTO
import org.jsoup.Connection
import org.jsoup.Jsoup
import java.nio.charset.Charset

class BoletimCrawler(user: User) : AuthenticatedCrawler(user), IScraper {
    override fun scrap(): BoletimDTO {
        val crawler = crawler("http://online.iesb.br/aonline/notas_freq_boletim_iframe.asp")
                .method(Connection.Method.GET)

        val doc = Jsoup.parse(
                String(crawler.execute().bodyAsBytes(), Charset.forName("ISO-8859-15"))
        )

        val boletim = doc.select("#Open_Text_General > tbody > tr")
                .map { row -> row.select("td").map { it.text() } }

        return BoletimDTO(boletim)
    }
}
