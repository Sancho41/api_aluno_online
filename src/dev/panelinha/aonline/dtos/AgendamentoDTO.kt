package dev.panelinha.aonline.dtos

class AgendamentoDTO : DTO() {
    class Agendamento(
        val numero: String,
        val motivoAtendimento: String,
        val dataHora: String,
        val situacao: String
    )

    val agendamentos: MutableList<Agendamento> = mutableListOf()

    fun addAgendamentos(dados: List<String>){
        agendamentos.add(
            Agendamento(
                dados[0],
                dados[1],
                dados[2],
                dados[3]
            )
        )
    }
}