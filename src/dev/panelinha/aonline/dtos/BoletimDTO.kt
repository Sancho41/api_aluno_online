package dev.panelinha.dev.panelinha.aonline.dtos

class BoletimDTO(
    disciplinas: List<List<String>>
) {
    class Disciplina(
        val codigo: String,
        val nome: String,
        val notaP1: Double,
        val notaP2: Double,
        val totalFaltas: Double,
        val cargaHoraria: Double,
        val porcentagemFrequencia: Double,
        val situacao: String
    )

    val disciplinas: List<Disciplina> = disciplinas.map {
        Disciplina(
            it[0],              // Código
            it[1],              // Disciplina
            it[2].toDoubleOrNull() ?: 0.0,   // P1
            it[3].toDoubleOrNull() ?: 0.0,   // P2
            it[10].toDoubleOrNull() ?: 0.0,  // Total Faltas
            it[12].toDoubleOrNull() ?: 0.0,  // Carga Horária
            it[13].toDoubleOrNull() ?: 0.0,  // Porcentagem de frequência
            it[14]              // Situação
        )
    }
}
