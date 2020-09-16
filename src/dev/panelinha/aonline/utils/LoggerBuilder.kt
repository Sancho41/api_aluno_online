package dev.panelinha.dev.panelinha.aonline.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

enum class LogType {
    INFO,
    ERROR,
    DEBUG,
    WARNING
}

var colors: Map<LogType, String> = mapOf(
    LogType.INFO to "\u001B[1;96m",
    LogType.ERROR to "\u001B[1;91m",
    LogType.WARNING to "\u001B[1;93m",
    LogType.DEBUG to "\u001B[1;90m"
)
var colorReset = "\u001B[0m"

class LoggerBuilder(private var text: Any, private var logType: LogType) {
    private var errorHandler: Error? = null

    companion object {
        fun DEBUG(text: Any) {
            val message = this.createMessage(LogType.DEBUG, text)
            LoggerBuilder(message, LogType.DEBUG).log()
        }

        fun INFO(text: Any) {
            val message = this.createMessage(LogType.INFO, text)
            LoggerBuilder(message, LogType.INFO).log()
        }

        fun WARNING(text: Any) {
            val message = this.createMessage(LogType.WARNING, text)
            LoggerBuilder(message, LogType.WARNING).log()
        }

        fun ERROR(text: Any, error: Error) {
            val message = this.createMessage(LogType.ERROR, text)
            LoggerBuilder(message, LogType.ERROR).setError(error).log()
        }

        private fun createMessage(logType: LogType, text: Any): String {
            val logTypeString = "[${colors[logType]}${logType}$colorReset]"
            return "$logTypeString ${this.dateNow()} $text"
        }

        private fun dateNow(): String {
            val current = LocalDateTime.now()

            val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            return current.format(formatter)
        }
    }

    fun setError(error: Error): LoggerBuilder {
        this.errorHandler = error;
        return this;
    }

    private fun log() {
        println(this.text)
        if (this.logType == LogType.ERROR)
            this.errorHandler?.printStackTrace()
    }
}
