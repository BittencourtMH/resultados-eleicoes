package controlador

import java.net.*
import java.util.*
import javafx.application.*
import javafx.fxml.*
import javafx.scene.control.*
import kotlinx.coroutines.*
import modelo.entidades.*

class ApresentacaoController : Initializable {
    @FXML
    lateinit var eleicao: Label

    @FXML
    lateinit var tipoConsulta: Label

    @FXML
    lateinit var cargo: Label

    @FXML
    lateinit var uf: Label

    @FXML
    lateinit var municipio: Label

    private lateinit var consultas: List<Consulta>

    private suspend fun atualizarModelo() {
        while (true) {
            consultas.forEach {
                it.resultado = Resultado(Tse.resultadoVariavel(it))
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
                    uf.text = it.uf.ds
                    municipio.text = it.municipio?.nm
                }
                delay(it.duracao * 1000L)
            }
        }
    }

    fun iniciar(consultas: List<Consulta>) {
        this.consultas = consultas
        GlobalScope.launch { atualizarModelo() }
        GlobalScope.launch { atualizarVisao() }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
    }
}
