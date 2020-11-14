package dev.panelinha.aonline.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class FloatParser {
    companion object {
        fun parse(valor: String): Float {
            val simbols = DecimalFormatSymbols()
            simbols.decimalSeparator = ','
            simbols.groupingSeparator = '.'
            val format = DecimalFormat()
            format.decimalFormatSymbols = simbols

            return format.parse(valor.ifEmpty {"0,0"}).toFloat()

        }
    }
}