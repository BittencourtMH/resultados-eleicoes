package controlador

import java.net.*
import java.util.*
import javafx.collections.*
import javafx.fxml.*
import javafx.scene.control.*
import javafx.util.*
import kotlin.collections.ArrayList
import modelo.entidades.*
import modelo.json.*

class Controller : Initializable {
    @FXML
    lateinit var eleicao: ChoiceBox<Eleicao>

    @FXML
    lateinit var tipoConsulta: ChoiceBox<TipoConsulta>

    @FXML
    lateinit var cargo: ChoiceBox<CargoPergunta>

    private fun configurarEleicao() {
        eleicao.converter = object : StringConverter<Eleicao>() {
            override fun toString(`object`: Eleicao?): String {
                return `object`!!.nm
            }

            override fun fromString(string: String?): Eleicao {
                return eleicao.items.find { it.nm == string }!!
            }
        }
        eleicao.items = FXCollections.observableArrayList(Tse.eleicaoConfiguracao().pl[0].e)
        eleicao.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
            cargo.items = FXCollections.observableArrayList(newValue.abr[0].cp)
            cargo.selectionModel.selectFirst()
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

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        configurarCargo()
        configurarEleicao()
        configurarTipoConsulta()
    }
}
