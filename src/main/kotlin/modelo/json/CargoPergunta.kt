package modelo.json

import kotlinx.serialization.*

@Serializable
data class CargoPergunta(
    val cd: String,
    val ds: String,
    val tp: String
)
