package modelo.json

import kotlinx.serialization.*

@Serializable
data class Resposta(
    val n: String,
    val ds: String
)
