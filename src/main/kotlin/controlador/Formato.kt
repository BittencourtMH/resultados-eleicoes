package controlador

import java.util.*

class Formato {
    companion object {
        fun long(long: Long): String {
            return "%,d".format(Locale("pt"), long)
        }

        fun long(string: String): String {
            return long(string.toLong())
        }

        fun porcento(long: Long): String {
            return "%d,%02d%%".format(long / 100, long % 100)
        }

        fun longPorcento(long: Long, porcento: Long): String {
            return "${long(long)} (${porcento(porcento)})"
        }

        fun longPorcento(long: String, porcento: Long): String {
            return longPorcento(long.toLong(), porcento)
        }

        fun longPorcento(long: String, porcento: String): String {
            return longPorcento(long.toLong(), Porcento.toLong(porcento))
        }
    }
}
