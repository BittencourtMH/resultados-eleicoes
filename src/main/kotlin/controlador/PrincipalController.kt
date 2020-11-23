package controlador

import java.net.*
import java.util.*
import javafx.beans.property.*
import javafx.collections.*
import javafx.fxml.*
import javafx.scene.*
import javafx.scene.control.*
import javafx.stage.*
import javafx.util.*
import kotlin.collections.ArrayList
import modelo.entidades.*
import modelo.json.*


class PrincipalController : Initializable {
    @FXML
    lateinit var eleicao: ChoiceBox<Eleicao>

    @FXML
    lateinit var tipoConsulta: ChoiceBox<TipoConsulta>

    @FXML
    lateinit var cargo: ChoiceBox<CargoPergunta>

    @FXML
    lateinit var uf: ComboBox<AbrangenciaMunicipio>

    @FXML
    lateinit var municipio: ComboBox<Municipio>

    @FXML
    lateinit var zonaEleitoral: ComboBox<String>

    @FXML
    lateinit var duracao: ChoiceBox<Int>

    @FXML
    lateinit var tableView: TableView<Consulta>

    @FXML
    lateinit var eleicaoColumn: TableColumn<Consulta, String>

    @FXML
    lateinit var tipoConsultaColumn: TableColumn<Consulta, String>

    @FXML
    lateinit var cargoColumn: TableColumn<Consulta, String>

    @FXML
    lateinit var ufColumn: TableColumn<Consulta, String>

    @FXML
    lateinit var municipioColumn: TableColumn<Consulta, String>

    @FXML
    lateinit var duracaoColumn: TableColumn<Consulta, Number>

    lateinit var municipioConfiguracao: MunicipioConfiguracao

    @FXML
    fun adicionar() {
        val consulta =
            Consulta(eleicao.value, tipoConsulta.value, cargo.value, uf.value, municipio.value, duracao.value)
        tableView.items.add(consulta)
    }

    @FXML
    fun apresentar() {
        val fxmlLoader = FXMLLoader()
        fxmlLoader.location = javaClass.getResource("/apresentacao.fxml")
        val stage = Stage()
        stage.scene = Scene(fxmlLoader.load())
        fxmlLoader.getController<ApresentacaoController>().iniciar(tableView.items)
        stage.show()
    }

    private fun configurarDuracao() {
        duracao.items = FXCollections.observableArrayList((5..60 step 5).toList())
    }

    private fun configurarEleicao() {
        eleicao.converter = object : StringConverter<Eleicao>() {
            override fun toString(`object`: Eleicao?): String {
                return `object`!!.nm
            }

            override fun fromString(string: String?): Eleicao {
                return eleicao.items.find { it.nm == string }!!
            }
        }
        val eleicaoConfiguracao = Tse.eleicaoConfiguracao()
        eleicao.items = FXCollections.observableArrayList(eleicaoConfiguracao.pl[0].e)
        eleicao.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
            cargo.items = FXCollections.observableArrayList(newValue.abr[0].cp)
            cargo.selectionModel.selectFirst()
            municipioConfiguracao = Tse.municipioConfiguracao(newValue.cd)
            uf.items = FXCollections.observableArrayList(municipioConfiguracao.abr)
            uf.selectionModel.selectFirst()
        }
        eleicao.selectionModel.selectFirst()
    }

    private fun configurarTipoConsulta() {
        tipoConsulta.converter = object : StringConverter<TipoConsulta>() {
            override fun toString(`object`: TipoConsulta?): String {
                return `object`!!.texto
            }

            override fun fromString(string: String?): TipoConsulta {
                return TipoConsulta.valueOf(string!!)
            }
        }
        tipoConsulta.items = FXCollections.observableArrayList(TipoConsulta.values().toCollection(ArrayList()))
        tipoConsulta.selectionModel.selectFirst()
    }

    private fun configurarCargo() {
        cargo.converter = object : StringConverter<CargoPergunta>() {
            override fun toString(`object`: CargoPergunta?): String {
                return `object`!!.ds
            }

            override fun fromString(string: String?): CargoPergunta {
                return eleicao.value.abr[0].cp.find { it.ds == string }!!
            }
        }
        cargo.selectionModel.selectFirst()
    }

    private fun configurarUf() {
        uf.converter = object : StringConverter<AbrangenciaMunicipio>() {
            override fun toString(`object`: AbrangenciaMunicipio?): String {
                return `object`?.ds ?: ""
            }

            override fun fromString(string: String?): AbrangenciaMunicipio {
                return municipioConfiguracao.abr.find { it.ds == string }!!
            }
        }
        uf.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
            municipio.selectionModel.clearSelection()
            if (newValue != null) municipio.items = FXCollections.observableArrayList(newValue.mu)
        }
    }

    private fun configurarMunicipio() {
        municipio.converter = object : StringConverter<Municipio>() {
            override fun toString(`object`: Municipio?): String {
                return `object`?.nm ?: ""
            }

            override fun fromString(string: String?): Municipio {
                return uf.value.mu.find { it.nm == string }!!
            }
        }
        municipio.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
            zonaEleitoral.selectionModel.clearSelection()
            if (newValue != null) zonaEleitoral.items = FXCollections.observableArrayList(newValue.z)
        }
    }

    private fun configurarTable() {
        eleicaoColumn.setCellValueFactory { SimpleStringProperty(it.value.eleicao.nm) }
        tipoConsultaColumn.setCellValueFactory { SimpleStringProperty(it.value.tipoConsulta.texto) }
        cargoColumn.setCellValueFactory { SimpleStringProperty(it.value.cargo.ds) }
        ufColumn.setCellValueFactory { SimpleStringProperty(it.value.uf.ds) }
        municipioColumn.setCellValueFactory { SimpleStringProperty(it.value.municipio?.nm) }
        duracaoColumn.setCellValueFactory { SimpleIntegerProperty(it.value.duracao) }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        configurarDuracao()
        configurarCargo()
        configurarMunicipio()
        configurarUf()
        configurarEleicao()
        configurarTipoConsulta()
        configurarTable()
    }
}
