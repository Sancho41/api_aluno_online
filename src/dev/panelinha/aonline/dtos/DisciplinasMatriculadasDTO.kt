package dev.panelinha.aonline.dtos

class DisciplinasMatriculadasDTO : DTO() {

    class DisciplinaM(
            val cod: String,
            val disciplina: String,
            val turma: String,
            val cred: Float,
            val aula: Float,
            val prat: Float,
            val est: Float,
            val situacao: String
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