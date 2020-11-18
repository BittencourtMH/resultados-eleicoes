import javafx.application.*
import javafx.fxml.*
import javafx.scene.*
import javafx.stage.*

class Main : Application() {
    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        val root = FXMLLoader.load<Parent>(javaClass.getResource("sample.fxml"))
        primaryStage.title = "Hello World"
        primaryStage.scene = Scene(root, 300.0, 275.0)
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}
