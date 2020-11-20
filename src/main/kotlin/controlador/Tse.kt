package controlador

import java.net.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import modelo.json.*
import org.apache.commons.text.*

class Tse {
    companion object {
        private const val HOST = "https://resultados.tse.jus.br/oficial"
        private const val AMBIENTE = "oficial"

        fun eleicaoConfiguracao(): EleicaoConfiguracao {
            return obter("$HOST/comum/divulgacao/$AMBIENTE/config/ele-c.json", EleicaoConfiguracao.serializer())
        }

        fun municipioConfiguracao(ciclo: String, eleicao: String): MunicipioConfiguracao {
            val url = "$HOST/$ciclo/divulgacao/$AMBIENTE/$eleicao/config/mun-e${eleicao.padStart(6, '0')}-cm.json"
            return obter(url, MunicipioConfiguracao.serializer())
        }

        private fun <T> obter(url: String, strategy: DeserializationStrategy<T>): T {
            return Json.decodeFromString(strategy, StringEscapeUtils.unescapeXml(URL(url).readText()))
        }
    }
}
