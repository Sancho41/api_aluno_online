package dev.panelinha.dev.panelinha.aonline.dao.historico

import dev.panelinha.aonline.models.User
import org.jsoup.select.Elements

class DisciplinasCursadasDAO(user: User) : HistoricoPageDAO(
    user,
    "#ctnTabPagina2 > table > tbody > tr > td > b > b > table > tbody > tr"
) {
    override fun getRows(): List<Elements> {
        val qtdColunas = getHeaders().size
        return super.getRows().filter { it.size == qtdColunas }
    }
}
