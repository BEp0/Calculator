package br.feevale.calculator

interface calculate {
    fun apply(expression: String): Double
    fun apply(value1: Double, value2: Double): Double
}


enum class OperationEnum(val type: String) : calculate {
    SUM("+") {
        override fun apply(expression: String): Double {
            val valuesToApplyOperation = expression.split("+")
            return valuesToApplyOperation[0].toDouble() + valuesToApplyOperation[1].toDouble()
        }

        override fun apply(value1: Double, value2: Double) = value1 + value2

    },
    SUB("-") {
        override fun apply(expression: String): Double {
            val valuesToApplyOperation = expression.split("-")
            return valuesToApplyOperation[0].toDouble() - valuesToApplyOperation[1].toDouble()
        }

        override fun apply(value1: Double, value2: Double) = value1 - value2
    },
    MULT("*") {
        override fun apply(expression: String): Double {
            val valuesToApplyOperation = expression.split("*")
            return valuesToApplyOperation[0].toDouble() * valuesToApplyOperation[1].toDouble()
        }

        override fun apply(value1: Double, value2: Double) = value1 * value2
    },
    DIV("/") {
        override fun apply(expression: String): Double {
            val valuesToApplyOperation = expression.split("/")
            if(valuesToApplyOperation[1].toDouble() == 0.0){
                throw RuntimeException("Não pode dividir por zero")
            }
            return valuesToApplyOperation[0].toDouble() / valuesToApplyOperation[1].toDouble()
        }

        override fun apply(value1: Double, value2: Double) =
            if(value2 == 0.0) throw RuntimeException("Não pode dividir por zero")
            else value1 / value2
    }
}