package dev.panelinha.aonline.dtos

import dev.panelinha.aonline.utils.FloatParser

class DisciplinasForadaGradeDTO {

    class Disciplina(

            val codigo: String,
            val disciplina: String,
            val turma: String,
            val credito: Float,
            val aula: Float,
            val media: String,
            val frequencia: Float,
            val situacao: String

    )

    val disciplinasForadaGrade: MutableList<Disciplina> = mutableListOf()

    fun addDisciplina(dados: List<String>){

        disciplinasForadaGrade.add(

                Disciplina(

                        dados[0],
                        dados[1],
                        dados[2],
                        FloatParser.parse(dados[3]),
                        FloatParser.parse(dados[4]),
                        dados[5],
                        FloatParser.parse(dados[6]),
                        dados[7]
                )
        )
    }
}