package controlador

import java.util.*

class Formato {
    companion object {
        fun int(int: Int): String {
            return "%,d".format(Locale("pt"), int)
        }

        fun int(string: String): String {
            return int(string.toInt())
        }

        fun porcento(int: Int): String {
            return "%d,%02d%%".format(int / 100, int % 100)
        }

        fun intPorcento(inteiro: Int, porcento: Int): String {
            return "${int(inteiro)} (${porcento(porcento)})"
        }

        fun intPorcento(inteiro: String, porcento: Int): String {
            return intPorcento(inteiro.toInt(), porcento)
        }

        fun intPorcento(inteiro: String, porcento: String): String {
            return intPorcento(inteiro.toInt(), Porcento.toInt(porcento))
        }
    }
}
