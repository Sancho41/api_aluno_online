package dev.panelinha.aonline.dtos

class DisciplinasMatriculadasDMDTO : DTO() {

    class Disciplinas(
            val disciplina: String,
            val situacaoDaDisciplina: String,
            val anoPer: String,
            val docente: String,
            val email: String
    )

    val disciplinasMatriculadasDM: MutableList<Disciplinas> = mutableListOf()

    fun addDisciplina(dados: List<String>){

        disciplinasMatriculadasDM.add(
                Disciplinas(
                        dados[0],
                        dados[1],
                        dados[2],
                        dados[3],
                        dados[4]
                )
        )

    }
}