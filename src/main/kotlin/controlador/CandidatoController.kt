package controlador

import java.net.*
import java.util.*
import javafx.beans.property.*
import javafx.fxml.*
import javafx.scene.control.*
import modelo.entidades.*

class CandidatoController : Initializable {
    @FXML
    lateinit var tableView: TableView<CandidatoTable>

    @FXML
    lateinit var ordem: TableColumn<CandidatoTable, String>

    @FXML
    lateinit var candidato: TableColumn<CandidatoTable, String>

    @FXML
    lateinit var partido: TableColumn<CandidatoTable, String>

    @FXML
    lateinit var votos: TableColumn<CandidatoTable, String>

    @FXML
    lateinit var situacao: TableColumn<CandidatoTable, String>

    private fun configurarTable() {
        ordem.setCellValueFactory { SimpleStringProperty(it.value.candidatoResposta.seq) }
        candidato.setCellValueFactory { SimpleStringProperty(it.value.candidato.nmu) }
        partido.setCellValueFactory { SimpleStringProperty(it.value.partido) }
        votos.setCellValueFactory {
            SimpleStringProperty(Formato.longPorcento(it.value.candidatoResposta.vap, it.value.candidatoResposta.pvap))
        }
        situacao.setCellValueFactory { SimpleStringProperty(it.value.candidatoResposta.st) }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        configurarTable()
    }
}
