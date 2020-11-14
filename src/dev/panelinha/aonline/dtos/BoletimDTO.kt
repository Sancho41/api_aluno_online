package dev.panelinha.dev.panelinha.aonline.dtos

class BoletimDTO(disciplinas: List<List<String>>) {
    class Disciplina(
        val codigo: String,
        val nome: String,
        val notaP1: Float,
        val notaP2: Float,
        val notaP3: Float,
        val totalFaltas: Float,
        val cargaHoraria: Float,
        val porcentagemFrequencia: Float,
        val situacao: String
    )

    val disciplinas: List<Disciplina>

    init {
        this.disciplinas = disciplinas.map {
            val offset: Int = if (it.size > 11) 0  else 6
            Disciplina(
                    it[0], // Código
                    it[1], // Disciplina
                    it[2].toFloatOrNull() ?: 0.0F, // P1
                    it[3].toFloatOrNull() ?: 0.0F, // P2
                    it[4].toFloatOrNull() ?: 0.0F, // P3
                    it[11 - offset].toFloatOrNull() ?: 0.0F, // Total Faltas
                    it[12 - offset].toFloatOrNull() ?: 0.0F, // Carga Horária
                    it[14 - offset].toFloatOrNull() ?: 0.0F, // Porcentagem de frequência
                    it[15 - offset] // Situação
            )
        }
    }
}
