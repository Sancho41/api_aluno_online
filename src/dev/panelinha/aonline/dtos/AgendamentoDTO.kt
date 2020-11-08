package dev.panelinha.dev.panelinha.aonline.dtos

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class AgendamentoDTO {
    class Agendamento(
        val numero: String,
        val motivoAtendimento: String,
        val dataHora: String,
        val situacao: String
    )

    val Agendamentos: MutableList<Agendamento> = mutableListOf()

    fun addAgendamentos(dados: List<String>){
        val simbols = DecimalFormatSymbols()
        simbols.decimalSeparator = ','
        simbols.groupingSeparator = '.'
        val format = DecimalFormat()
        format.decimalFormatSymbols = simbols
        Agendamentos.add(
            Agendamento(
                dados[0],
                dados[1],
                dados[2],
                dados[3]
            )
        )
    }
}