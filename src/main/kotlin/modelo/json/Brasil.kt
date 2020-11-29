package modelo.json

import kotlinx.serialization.*

@Serializable
data class Brasil(
    val nm: String,
    val s: String? = null,
    val e: String? = null,
    val uf: List<Uf>
)
