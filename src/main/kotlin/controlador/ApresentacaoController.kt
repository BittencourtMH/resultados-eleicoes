package controlador

import javafx.application.*
import javafx.collections.*
import javafx.fxml.*
import javafx.scene.control.*
import javafx.scene.layout.*
import kotlinx.coroutines.*
import modelo.entidades.*
import modelo.json.*

class ApresentacaoController {
    @FXML
    lateinit var eleicao: Label

    @FXML
    lateinit var tipoConsulta: Label

    @FXML
    lateinit var cargo: Label

    @FXML
    lateinit var abrangencia: Label

    @FXML
    lateinit var secoesLabel: Label

    @FXML
    lateinit var secoesTotalizadasLabel: Label

    @FXML
    lateinit var secoesNaoTotalizadasLabel: Label

    @FXML
    lateinit var eleitoresLabel: Label

    @FXML
    lateinit var eleitoresApuradosLabel: Label

    @FXML
    lateinit var eleitoresNaoApuradosLabel: Label

    @FXML
    lateinit var comparecimentoLabel: Label

    @FXML
    lateinit var abstencaoLabel: Label

    @FXML
    lateinit var concorrentesLabel: Label

    @FXML
    lateinit var brancosLabel: Label

    @FXML
    lateinit var nulosLabel: Label

    @FXML
    lateinit var tabela: VBox

    private lateinit var consultas: List<Consulta>

    private suspend fun atualizarModelo() {
        while (true) {
            consultas.forEach {
                val resultadoVariavel = Tse.resultadoVariavel(it)
                val resultadoFixo =
                    if (it.resultado?.resultadoVariavel?.nadf == resultadoVariavel.nadf) it.resultado!!.resultadoFixo
                    else {
                        delay(1000)
                        Tse.resultadoFixo(it, resultadoVariavel.nadf)
                    }
                val abrangenciaVariavel =
                    if (it.zona != null) {
                        resultadoVariavel.abr.find { abrangencia -> abrangencia.cdabr.toInt() == it.zona.toInt() }!!
                    } else resultadoVariavel.abr.find { abrangencia -> abrangencia.tpabr != "ZONA" }!!
                val candidatosRespostas = mutableListOf<CandidatoRespostaTable>()
                if (resultadoFixo.carg != null) {
                    resultadoFixo.carg.col.forEach { coligacao ->
                        coligacao.par.forEach { partido ->
                            partido.cand.forEach { candidato ->
                                val candidatoResposta = abrangenciaVariavel.cand!!.find { candidatoResposta ->
                                    candidatoResposta.n == candidato.n
                                }
                                candidatosRespostas.add(CandidatoTable(candidatoResposta!!, candidato, partido.sg))
                            }
                        }
                    }
                } else {
                    resultadoFixo.perg!![0].resp.forEach { resposta ->
                        val candidatoResposta = abrangenciaVariavel.resp!!.find { candidatoResposta ->
                            candidatoResposta.n == resposta.n
                        }
                        candidatosRespostas.add(RespostaTable(candidatoResposta!!, resposta))
                    }
                }
                candidatosRespostas.sortBy { candidatoTable -> candidatoTable.candidatoResposta.seq.toInt() }
                it.resultado = Resultado(resultadoVariavel, resultadoFixo, abrangenciaVariavel, candidatosRespostas)
                delay(it.duracao * 1000L)
            }
        }
    }

    private suspend fun atualizarVisao() {
        while (true) {
            consultas.forEach {
                Platform.runLater {
                    eleicao.text = it.eleicao.nm
                    tipoConsulta.text = it.tipoConsulta.texto
                    cargo.text = it.cargo.ds
                    abrangencia.text = if (it.zona != null) "Zona ${it.zona}, " else ""
                    if (it.municipio != null) abrangencia.text += "${it.municipio.nm}, "
                    abrangencia.text += it.uf.cd
                    val resultado = it.resultado
                    if (resultado != null) {
                        atualizarVisaoVariavel(resultado.abrangenciaVariavel)
                        carregarCandidatoResposta(resultado)
                    }
                }
                delay(it.duracao * 1000L)
            }
        }
    }

    private fun atualizarVisaoVariavel(abrangencia: AbrangenciaVariavel) {
        val porcentoEleitoresApurados = Porcento.calculo(abrangencia.ea, abrangencia.e)
        val eleitoresNaoApurados = abrangencia.e.toInt() - abrangencia.ea.toInt()
        val porcentoEleitoresNaoApurados = Porcento.calculo(eleitoresNaoApurados, abrangencia.e)
        secoesLabel.text = Formato.int(abrangencia.s)
        secoesTotalizadasLabel.text = Formato.intPorcento(abrangencia.st, abrangencia.pst)
        secoesNaoTotalizadasLabel.text = Formato.intPorcento(abrangencia.snt, abrangencia.psnt)
        eleitoresLabel.text = Formato.int(abrangencia.e)
        eleitoresApuradosLabel.text = Formato.intPorcento(abrangencia.ea, porcentoEleitoresApurados)
        eleitoresNaoApuradosLabel.text = Formato.intPorcento(eleitoresNaoApurados, porcentoEleitoresNaoApurados)
        comparecimentoLabel.text = Formato.intPorcento(abrangencia.c, abrangencia.pc)
        abstencaoLabel.text = Formato.intPorcento(abrangencia.a, abrangencia.pa)
        concorrentesLabel.text = Formato.intPorcento(abrangencia.vvc, abrangencia.pvvc)
        brancosLabel.text = Formato.intPorcento(abrangencia.vb, abrangencia.pvb)
        nulosLabel.text = Formato.intPorcento(abrangencia.tvn, abrangencia.ptvn)
    }

    private fun carregarCandidatoResposta(resultado: Resultado) {
        val resource = if (resultado.resultadoFixo.carg != null) "/candidato.fxml" else "/resposta.fxml"
        val tableView = FXMLLoader.load<TableView<CandidatoRespostaTable>>(javaClass.getResource(resource))
        tableView.items = FXCollections.observableArrayList(resultado.candidatosRespostas)
        tabela.children.setAll(tableView)
    }

    fun iniciar(consultas: List<Consulta>) {
        this.consultas = consultas
        GlobalScope.launch { atualizarModelo() }
        GlobalScope.launch { atualizarVisao() }
    }
}
