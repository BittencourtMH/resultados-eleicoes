package modelo.entidades

import modelo.json.*

data class RespostaTable(
    override val candidatoResposta: CandidatoResposta,
    val resposta: Resposta
) : CandidatoRespostaTable(candidatoResposta)
