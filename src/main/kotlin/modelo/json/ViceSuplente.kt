package modelo.json

import kotlinx.serialization.*

@Serializable
data class ViceSuplente(
    val tp: String,
    val sqcand: String,
    val nm: String,
    val nmu: String,
    val sgp: String
)
