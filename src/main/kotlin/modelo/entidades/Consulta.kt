package modelo.entidades

import modelo.json.*

data class Consulta(
    val eleicao: Eleicao,
    val tipoConsulta: TipoConsulta,
    val cargo: CargoPergunta,
    val uf: AbrangenciaMunicipio,
    val municipio: Municipio? = null,
    val zona: String? = null,
    val duracao: Int,
    var resultado: Resultado? = null
)
