package modelo.json

import kotlinx.serialization.*

@Serializable
data class Eleicao(val cd: String, val cdt2: String, val nm: String, val t: String, val tp: String,
                   val abr: List<Abrangencia>)
