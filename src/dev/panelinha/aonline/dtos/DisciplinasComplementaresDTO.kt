package dev.panelinha.aonline.dtos

import dev.panelinha.aonline.utils.FloatParser

class DisciplinasComplementaresDTO : DTO() {
    class Disciplina(
            val anoPer: String,
            val atividade: String,
            val grupo: String,
            val tipo: String,
            val cargaHorariaCumprida: Float
    )

    val disciplinasComplementares: MutableList<Disciplina> = mutableListOf()

    fun addDisciplina(dados: List<String>) {
        disciplinasComplementares.add(
                Disciplina(
                        dados[0],
                        dados[1],
                        dados[2],
                        dados[3],
                        FloatParser.parse(dados[4])
                )
        )

    }
}