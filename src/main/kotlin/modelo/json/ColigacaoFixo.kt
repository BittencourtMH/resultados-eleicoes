package modelo.json

import kotlinx.serialization.*

@Serializable
data class ColigacaoFixo(
    val n: String,
    val nm: String,
    val tp: String,
    val com: String,
    val par: List<PartidoFixo>
)
