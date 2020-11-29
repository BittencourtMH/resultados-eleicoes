package modelo.json

import kotlinx.serialization.*

@Serializable
data class Pergunta(
    val cd: String,
    val ds: String,
    val resp: List<Resposta>
)
