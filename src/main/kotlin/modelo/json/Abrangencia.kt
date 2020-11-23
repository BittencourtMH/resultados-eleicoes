package modelo.json

import kotlinx.serialization.*

@Serializable
data class Abrangencia(
    val cd: String,
    val mu: List<Municipio>? = null,
    val cp: List<CargoPergunta>
)
