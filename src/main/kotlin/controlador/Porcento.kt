package controlador

import kotlin.math.*

class Porcento {
    companion object {
        fun calculo(valor: Long, total: Long): Long {
            if (valor == 0L) return 0
            if (valor == total) return 10000
            return max(1, min(9999, (valor * 10000 + total / 2) / total))
        }

        fun calculo(valor: Long, total: String): Long {
            return calculo(valor, total.toLong())
        }

        fun calculo(valor: String, total: String): Long {
            return calculo(valor.toLong(), total.toLong())
        }

        fun toLong(string: String): Long {
            return string.replace(Regex("\\D"), "").toLong()
        }
    }
}
