package modelo.json

import kotlinx.serialization.*

@Serializable
data class CandidatoResposta(
    val seq: String,
    val n: String,
    val e: String,
    val st: String,
    val vap: String,
    val pvap: String
)
