package dev.panelinha.dev.panelinha.aonline.dao.historico

import dev.panelinha.aonline.models.User

class DisciplinasMatriculasdasDAO(user: User): HistoricoPageDAO(
    user,
    "#ctnTabPagina2 > table > tbody > tr > td > b > b > b > b > table > tbody > tr"
)