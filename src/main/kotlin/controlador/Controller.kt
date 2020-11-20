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

    @FXML
    lateinit var uf: ComboBox<AbrangenciaMunicipio>

    @FXML
    lateinit var municipio: ComboBox<Municipio>

    @FXML
    lateinit var zonaEleitoral: ComboBox<String>

    lateinit var municipioConfiguracao: MunicipioConfiguracao

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
            municipioConfiguracao = Tse.municipioConfiguracao(eleicaoConfiguracao.c, newValue.cd)
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

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        configurarCargo()
        configurarMunicipio()
        configurarUf()
        configurarEleicao()
        configurarTipoConsulta()
    }
}
