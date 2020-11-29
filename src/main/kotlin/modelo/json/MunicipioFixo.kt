package modelo.json

import kotlinx.serialization.*

@Serializable
data class MunicipioFixo(
    val cd: String,
    val nm: String,
    val c: String,
    val s: String,
    val e: String,
    val tzon: String,
    val zon: List<Zona>
)
