package modelo.json

import kotlinx.serialization.*

@Serializable
data class Uf(
    val sg: String,
    val nm: String,
    val s: String? = null,
    val e: String? = null,
    val tmun: String? = null,
    val mun: List<MunicipioFixo>? = null
)
