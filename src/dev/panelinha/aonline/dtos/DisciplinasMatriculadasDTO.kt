package dev.panelinha.dev.panelinha.aonline.dtos

class DisciplinasMatriculadasDTO {

    class DisciplinaM(
            val Cod: String,
            val Disciplina: String,
            val Turma: String,
            val Cred: Float,
            val Aula: Float,
            val Prat: Float,
            val Est: Float,
            val Situacao: String
    )


    val disciplinasMatriculadas: MutableList<DisciplinaM> = mutableListOf()

    fun addDisciplina(dados: List<String>){

        disciplinasMatriculadas.add(
                DisciplinaM(
                        dados[0],
                        dados[1],
                        dados[2],
                        dados[3].toFloatOrNull() ?: 0.0F,
                        dados[4].toFloatOrNull() ?: 0.0F,
                        dados[5].toFloatOrNull() ?: 0.0F,
                        dados[6].toFloatOrNull() ?: 0.0F,
                        dados[7]
                )
        )
    }
}