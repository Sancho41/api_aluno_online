package dev.panelinha.dev.panelinha.aonline.dao.historico

import dev.panelinha.aonline.models.User
import dev.panelinha.dev.panelinha.aonline.dao.PageDAO
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

open class HistoricoPageDAO(user: User, seletorTabela: String) : PageDAO() {
    private val connectionUrl: String = "http://online.iesb.br/aonline/historico.asp"
    private val page: Document
    private val tabela: Elements

    init {
        this.page = getConnection(connectionUrl, user).get()
        this.tabela = page.select(seletorTabela)
    }

    protected fun getHeaders(): List<String> {
        return tabela[0].select("td").map { it.text() }
    }

    open fun getRows(): List<Elements> {
        val rows = tabela.toMutableList()
        rows.removeAt(0)
        rows.removeAt(rows.size - 1)
        rows.removeAt(rows.size - 1)
        return rows.map { it.select("td") }
    }

    open fun getTable(): List<Map<String, String>> {
        val headers = getHeaders()
        val tabela = mutableListOf<Map<String, String>>()

        for (row in getRows()) {
            val newRow = mutableMapOf<String, String>()
            for ((index, valor) in headers.withIndex())
                newRow[valor] = row[index].text()
            tabela.add(newRow)
        }
        return tabela
    }
}
