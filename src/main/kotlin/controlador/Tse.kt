package controlador

import java.net.*
import kotlinx.serialization.json.*
import modelo.json.*

class Tse {
    companion object {
        private const val HOST = "https://resultados.tse.jus.br/oficial"
        private const val AMBIENTE = "oficial"

        fun eleicaoConfiguracao(): EleicaoConfiguracao {
            val json = URL("$HOST/comum/divulgacao/$AMBIENTE/config/ele-c.json").readText()
            return Json.decodeFromString(EleicaoConfiguracao.serializer(), json)
        }
    }
}
