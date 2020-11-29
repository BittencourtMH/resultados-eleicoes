package controlador

import java.net.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import modelo.entidades.*
import modelo.json.*
import org.apache.commons.text.*

class Tse {
    companion object {
        private const val HOST = "https://resultados.tse.jus.br/oficial"
        private const val AMBIENTE = "oficial"
        private lateinit var ciclo: String

        fun eleicaoConfiguracao(): EleicaoConfiguracao {
            val url = "$HOST/comum/divulgacao/$AMBIENTE/config/ele-c.json"
            val eleicaoConfiguracao = obter(url, EleicaoConfiguracao.serializer())
            ciclo = eleicaoConfiguracao.c
            return eleicaoConfiguracao
        }

        fun municipioConfiguracao(eleicao: String): MunicipioConfiguracao {
            val url = "$HOST/$ciclo/divulgacao/$AMBIENTE/$eleicao/config/mun-e${eleicao.padStart(6, '0')}-cm.json"
            return obter(url, MunicipioConfiguracao.serializer())
        }

        fun resultadoVariavel(consulta: Consulta): ResultadoVariavel {
            val uf = consulta.uf.cd.toLowerCase()
            val cargo = consulta.cargo.cd.padStart(4, '0')
            val arquivo = "$uf${consulta.municipio?.cd ?: ""}-c$cargo-e${consulta.eleicao.cd.padStart(6, '0')}-v.json"
            val url = "$HOST/$ciclo/divulgacao/$AMBIENTE/${consulta.eleicao.cd}/dados/$uf/$arquivo"
            return obter(url, ResultadoVariavel.serializer())
        }

        fun resultadoFixo(consulta: Consulta, arquivo: String): ResultadoFixo {
            val uf = consulta.uf.cd.toLowerCase()
            val url = "$HOST/$ciclo/divulgacao/$AMBIENTE/${consulta.eleicao.cd}/dados/$uf/$arquivo.json"
            return obter(url, ResultadoFixo.serializer())
        }

        private fun <T> obter(url: String, strategy: DeserializationStrategy<T>): T {
            return Json.decodeFromString(strategy, StringEscapeUtils.unescapeXml(URL(url).readText()))
        }
    }
}
