package dev.panelinha.aonline.modules

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import dev.panelinha.aonline.models.User

object JwtConfig {
    private val secret: String = System.getenv("SECRET") ?: "My-super-secrete-secrete"
    private const val issuer = "dev.panelinha"
    private val algorithm = Algorithm.HMAC512(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    fun generateToken(user: User): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("email", user.email)
        .withClaim("chave", user.credenciaisAO?.chave)
        .sign(algorithm)
}