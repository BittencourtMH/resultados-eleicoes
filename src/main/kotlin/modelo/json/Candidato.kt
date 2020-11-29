package modelo.json

import kotlinx.serialization.*

@Serializable
data class Candidato(
    val n: String,
    val sqcand: String,
    val nm: String,
    val nmu: String,
    val s: String,
    val dt: String,
    val dvt: String,
    val vs: List<ViceSuplente>? = null,
    val subs: List<Substituido>? = null
)
