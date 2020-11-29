package modelo.json

import kotlinx.serialization.*

@Serializable
data class Substituido(
    val nm: String,
    val nmu: String,
    val sgp: String
)
