package dev.panelinha.dev.panelinha.aonline.dtos

import dev.panelinha.aonline.utils.FloatParser
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class FinanceiroDTO {
    class Parcelas(
            val vencimento: String,
            val anoMes: String,
            val boleto: Long,
            val lancado: Float,
            val descontoParcelas: Float,
            val bolsa: Float,
            val valorFinal: Float,
            val acerto: Float,
            val aPagar: Float,
            val descontoDados: Float,
            val encargoCalculado: Float,
            val valorPago: Float,
            val chequeSemFundo: Float,
            val valorSemFundo: Float
    )

    val extratos: MutableList<Parcelas> = mutableListOf()

    fun addExtrato(dados: List<String>){
        extratos.add(
                Parcelas(
                     dados[0],
                     dados[1],
                    dados[2].toLongOrNull() ?: 0,
                    FloatParser.parse(dados[3]),
                    FloatParser.parse(dados[4]),
                    FloatParser.parse(dados[5]),
                    FloatParser.parse(dados[6]),
                    FloatParser.parse(dados[7]),
                    FloatParser.parse(dados[8]),
                    FloatParser.parse(dados[9]),
                    FloatParser.parse(dados[10]),
                    FloatParser.parse(dados[11]),
                    FloatParser.parse(dados[12]),
                    FloatParser.parse(dados[13])
                )
        )

    }
}