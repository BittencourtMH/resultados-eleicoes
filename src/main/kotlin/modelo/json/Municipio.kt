package modelo.json

import kotlinx.serialization.*

@Serializable
data class Municipio(
    val cd: String,
    val cdi: String,
    val nm: String? = null,
    val c: String? = null,
    val z: List<String>? = null
)
