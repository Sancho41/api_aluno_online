package dev.panelinha.dev.panelinha.aonline.dtos

class DisciplinasCursadasDTO {
    class Disciplina(
        val Ano: String,
        val Codigo: String,
        val Disciplina: String,
        val Turma: String,
        val Serie: Int,
        val Credito: Float,
        val Aula: Float,
        val Prat: Int,
        val Est: Int,
        val CargaHoraria: Int,
        val Media: String,
        val Faltas: Float,
        val Frequencia: Float,
        val Situacao: String
    ) {

    }

    val disciplinasCursadas: MutableList<Disciplina> = mutableListOf()

    fun addDisciplina(dados: List<String>) {
        disciplinasCursadas.add(
            Disciplina(
                dados[0],
                dados[1],
                dados[2],
                dados[3],
                dados[4].toIntOrNull() ?: 0,
                dados[5].toFloatOrNull() ?: 0.0F,
                dados[6].toFloatOrNull() ?: 0.0F,
                dados[7].toIntOrNull() ?: 0,
                dados[8].toIntOrNull() ?: 0,
                dados[9].toIntOrNull() ?: 0,
                dados[10],
                dados[11].toFloatOrNull() ?: 0.0F,
                dados[12].toFloatOrNull() ?: 0.0F,
                dados[13]
            )
        )
    }
}
