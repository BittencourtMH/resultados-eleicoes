package modelo.json

import kotlinx.serialization.*

@Serializable
data class EleicaoConfiguracao(
    val dg: String,
    val hg: String,
    val f: String,
    val c: String,
    val pl: List<Pleito>
)
