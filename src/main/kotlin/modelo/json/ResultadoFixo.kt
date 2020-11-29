package modelo.json

import kotlinx.serialization.*

@Serializable
data class ResultadoFixo(
    val ele: String,
    val cdabr: String,
    val nmabr: String,
    val t: String,
    val f: String,
    val s: String,
    val dg: String,
    val hg: String,
    val v: String,
    val br: Brasil,
    val carg: Cargo? = null,
    val perg: List<Pergunta>? = null
)
