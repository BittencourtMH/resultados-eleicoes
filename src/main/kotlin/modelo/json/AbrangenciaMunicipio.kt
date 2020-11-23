package modelo.json

import kotlinx.serialization.*

@Serializable
data class AbrangenciaMunicipio(
    val cd: String,
    val ds: String,
    val mu: List<Municipio>
)
