package dev.panelinha.dev.panelinha.aonline.dtos

class DisciplinasPendentesDTO {

    class DisciplinaPendente(

            val serie: Int,
            val cod: String,
            val disciplina: String,
            val cargaHoraria: Int,
            val creditos: Float

    )

    val disciplinasPendentes: MutableList<DisciplinaPendente> = mutableListOf()

    fun addDisciplinaP(dados: List<String>){

        disciplinasPendentes.add(
                DisciplinaPendente(
                        dados[0].toIntOrNull() ?: 0,
                        dados[1],
                        dados[2],
                        dados[3].toIntOrNull() ?: 0,
                        dados[4].toFloatOrNull() ?: 0.0F
                )
        )
    }
}