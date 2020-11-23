package modelo.json

import kotlinx.serialization.*

@Serializable
data class Partido(
    val n: String,
    val tvtn: String,
    val tvtl: String,
    val tvan: String,
    val tval: String
)
