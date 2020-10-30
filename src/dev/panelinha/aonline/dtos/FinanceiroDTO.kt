package dev.panelinha.dev.panelinha.aonline.dtos

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
        val simbols = DecimalFormatSymbols()
        simbols.decimalSeparator = ','
        simbols.groupingSeparator = '.'
        val format = DecimalFormat()
        format.decimalFormatSymbols = simbols
        extratos.add(
                Parcelas(
                        dados[0],
                        dados[1],
                        dados[2].toLongOrNull() ?: 0,
                        format.parse(dados[3].ifEmpty { "0,0" }).toFloat(),
                        format.parse(dados[4].ifEmpty { "0,0" }).toFloat(),
                        format.parse(dados[5].ifEmpty { "0,0" }).toFloat(),
                        format.parse(dados[6].ifEmpty { "0,0" }).toFloat(),
                        format.parse(dados[7].ifEmpty { "0,0" }).toFloat(),
                        format.parse(dados[8].ifEmpty { "0,0" }).toFloat(),
                        format.parse(dados[9].ifEmpty { "0,0" }).toFloat(),
                        format.parse(dados[10].ifEmpty { "0,0" }).toFloat(),
                        format.parse(dados[11].ifEmpty { "0,0" }).toFloat(),
                        format.parse(dados[12].ifEmpty { "0,0" }).toFloat(),
                        format.parse(dados[13].ifEmpty { "0,0" }).toFloat()
                )
        )

    }
}