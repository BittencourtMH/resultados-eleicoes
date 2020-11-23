package modelo.json

import kotlinx.serialization.*

@Serializable
data class Pleito(
    val cd: String,
    val cdpr: String,
    val dt: String,
    val dtlim: String,
    val e: List<Eleicao>
)
