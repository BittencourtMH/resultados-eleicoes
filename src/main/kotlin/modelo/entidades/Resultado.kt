package modelo.entidades

import modelo.json.*

data class Resultado(
    val resultadoVariavel: ResultadoVariavel,
    val resultadoFixo: ResultadoFixo,
    val abrangenciaVariavel: AbrangenciaVariavel,
    val candidatosRespostas: List<CandidatoRespostaTable>
)
