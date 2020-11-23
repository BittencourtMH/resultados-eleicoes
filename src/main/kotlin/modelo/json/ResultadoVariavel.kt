package modelo.json

import kotlinx.serialization.*

@Serializable
data class ResultadoVariavel(
    val ele: String,
    val carper: String,
    val t: String,
    val f: String,
    val esae: String,
    val mnae: String,
    val dg: String,
    val hg: String,
    val dv: String,
    val nadf: String,
    val abr: List<AbrangenciaVariavel>
)
