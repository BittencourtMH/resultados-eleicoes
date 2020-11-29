package controlador

import kotlin.math.*

class Porcento {
    companion object {
        fun calculo(valor: Int, total: Int): Int {
            if (valor == 0) return 0
            if (valor == total) return 10000
            return max(1, min(9999, (valor * 10000 + total / 2) / total))
        }

        fun calculo(valor: Int, total: String): Int {
            return calculo(valor, total.toInt())
        }

        fun calculo(valor: String, total: String): Int {
            return calculo(valor.toInt(), total.toInt())
        }

        fun toInt(string: String): Int {
            return string.replace(Regex("\\D"), "").toInt()
        }
    }
}
