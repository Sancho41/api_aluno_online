package dev.panelinha.dev.panelinha.aonline.dtos

class DisciplinasCursadasDTO {
    class Disciplina(
        val ano: String,
        val codigo: String,
        val disciplina: String,
        val turma: String,
        val serie: Int,
        val credito: Float,
        val aula: Float,
        val prat: Int,
        val est: Int,
        val cargaHoraria: Int,
        val media: String,
        val faltas: Float,
        val frequencia: Float,
        val situacao: String
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
