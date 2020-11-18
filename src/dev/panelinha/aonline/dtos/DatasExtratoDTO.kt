package dev.panelinha.aonline.dtos

class DatasExtratoDTO(var dataIni: String, var dataFim: String) {
    fun toMap(): Map<String, String> {
        return mapOf("dataIni" to dataIni, "dataFim" to dataFim)
    }
}
