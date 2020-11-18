package dev.panelinha.aonline.dtos

class ServicosSolicitadosDTO {
    class Servicos(
            val numero: String,
            val servico: String,
            val quantidade: Int,
            val solicitadoEm: String,
            val prazo: String,
            val situacao: String
    )

    val servicosSolicitados: MutableList<Servicos> = mutableListOf()

    fun addServicos(dados: List<String>) {
        servicosSolicitados.add(
                Servicos(
                        dados[0],
                        dados[1],
                        dados[2].toIntOrNull() ?: 0,
                        dados[3],
                        dados[4],
                        dados[5]
                )
        )
    }
}