package modelo.json

import kotlinx.serialization.*

@Serializable
data class Cargo(
    val cd: String,
    val nmn: String,
    val nmm: String,
    val nmf: String,
    val nv: String,
    val col: List<ColigacaoFixo>
)
