package dev.panelinha.aonline.exceptions

import java.lang.Exception

class InvalideCredentialsException (override var message: String): Exception(message) {
}