package controlador

import java.net.*
import java.util.*
import javafx.beans.property.*
import javafx.fxml.*
import javafx.scene.control.*
import modelo.entidades.*

class RespostaController : Initializable {
    @FXML
    lateinit var tableView: TableView<RespostaTable>

    @FXML
    lateinit var ordem: TableColumn<RespostaTable, String>

    @FXML
    lateinit var resposta: TableColumn<RespostaTable, String>

    @FXML
    lateinit var votos: TableColumn<RespostaTable, String>

    @FXML
    lateinit var situacao: TableColumn<RespostaTable, String>

    private fun configurarTable() {
        ordem.setCellValueFactory { SimpleStringProperty(it.value.candidatoResposta.seq) }
        resposta.setCellValueFactory { SimpleStringProperty(it.value.resposta.ds) }
        votos.setCellValueFactory {
            SimpleStringProperty(Formato.intPorcento(it.value.candidatoResposta.vap, it.value.candidatoResposta.pvap))
        }
        situacao.setCellValueFactory { SimpleStringProperty(it.value.candidatoResposta.st) }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        configurarTable()
    }
}
