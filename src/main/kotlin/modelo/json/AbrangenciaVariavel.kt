package modelo.json

import kotlinx.serialization.*

@Serializable
data class AbrangenciaVariavel(
    val dt: String,
    val ht: String,
    val tf: String,
    val and: String,
    val tpabr: String,
    val cdabr: String,
    val s: String,
    val st: String,
    val pst: String,
    val snt: String,
    val psnt: String,
    val si: String,
    val psi: String,
    val sni: String,
    val psni: String,
    val sa: String,
    val psa: String,
    val sna: String,
    val psna: String,
    val e: String,
    val ea: String,
    val pea: String,
    val ena: String,
    val pena: String,
    val esi: String,
    val pesi: String,
    val esni: String,
    val pesni: String,
    val c: String,
    val pc: String,
    val a: String,
    val pa: String,
    val tv: String,
    val vvc: String,
    val pvvc: String,
    val vscv: String? = null,
    val vb: String,
    val pvb: String,
    val tvn: String,
    val ptvn: String,
    val vn: String,
    val pvn: String,
    val vnt: String,
    val pvnt: String,
    val vp: String,
    val pvp: String,
    val vv: String,
    val pvv: String,
    val vl: String? = null,
    val pvl: String? = null,
    val van: String,
    val pvan: String,
    val vansj: String,
    val pvansj: String,
    val vnom: String? = null,
    val pvnom: String? = null,
    val colig: List<Coligacao>? = null,
    val part: List<Partido>? = null,
    val cand: List<CandidatoResposta>? = null,
    val resp: List<CandidatoResposta>? = null
)
