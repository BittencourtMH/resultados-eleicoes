import javafx.application.*
import javafx.fxml.*
import javafx.scene.*
import javafx.stage.*

class Main : Application() {
    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        val root = FXMLLoader.load<Parent>(javaClass.getResource("principal.fxml"))
        primaryStage.title = "Resultados Eleições"
        primaryStage.scene = Scene(root)
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}
