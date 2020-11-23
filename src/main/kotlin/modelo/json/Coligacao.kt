package modelo.json

import kotlinx.serialization.*

@Serializable
data class Coligacao(
    val n: String,
    val tvtn: String,
    val tvtl: String,
    val tvan: String,
    val tval: String,
    val vag: String? = null
)
