package dev.panelinha.aonline.dtos

class HorarioDeAulasDTO {
    class Disciplina (
        val materia: String,
        val turma: String,
        val local: String,
        val horario: String,
        val docente: String
    )

    val segunda: MutableList<Disciplina> = mutableListOf()
    val terca: MutableList<Disciplina> = mutableListOf()
    val quarta: MutableList<Disciplina> = mutableListOf()
    val quinta: MutableList<Disciplina> = mutableListOf()
    val sexta: MutableList<Disciplina> = mutableListOf()
    val sabado: MutableList<Disciplina> = mutableListOf()
    val domingo: MutableList<Disciplina> = mutableListOf()

    fun addMateria(diaDaSemana: String, dados: List<String>) {
        val (materia, turma, local, horario, docente) = dados
        val disciplina = Disciplina(materia, turma, local, horario, docente)
        when (diaDaSemana) {
            "SEGUNDA" -> segunda.add(disciplina)
            "TERÇA" -> terca.add(disciplina)
            "QUARTA" -> quarta.add(disciplina)
            "QUINTA" -> quinta.add(disciplina)
            "SEXTA" -> sexta.add(disciplina)
            "SÁBADO" -> sabado.add(disciplina)
            "DOMINGO" -> domingo.add(disciplina)
        }
    }
}
