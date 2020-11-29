package modelo.entidades

import modelo.json.*

data class CandidatoTable(
    override val candidatoResposta: CandidatoResposta,
    val candidato: Candidato,
    val partido: String
) : CandidatoRespostaTable(candidatoResposta)
