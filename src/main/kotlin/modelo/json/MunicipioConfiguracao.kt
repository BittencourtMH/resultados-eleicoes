package modelo.json

import kotlinx.serialization.*

@Serializable
data class MunicipioConfiguracao(val dg: String, val hg: String, val f: String, val abr: List<AbrangenciaMunicipio>)
