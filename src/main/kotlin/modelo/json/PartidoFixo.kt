package modelo.json

import kotlinx.serialization.*

@Serializable
data class PartidoFixo(
    val n: String,
    val sg: String,
    val nm: String,
    val cand: List<Candidato>
)
