package br.feevale.calculator

enum class OperationEnum(val type: String) : Calculate {
    SUM("+") {
        override fun apply(expression: String): Double {
            val valuesToApplyOperation = expression.split("+")
            return valuesToApplyOperation[0].toDouble() + valuesToApplyOperation[1].toDouble()
        }
    },
    SUB("-") {
        override fun apply(expression: String): Double {
            val valuesToApplyOperation = expression.split("-")
            return valuesToApplyOperation[0].toDouble() - valuesToApplyOperation[1].toDouble()
        }
    },
    MULT("*") {
        override fun apply(expression: String): Double {
            val valuesToApplyOperation = expression.split("*")
            return valuesToApplyOperation[0].toDouble() * valuesToApplyOperation[1].toDouble()
        }
    },
    DIV("/") {
        override fun apply(expression: String): Double {
            val valuesToApplyOperation = expression.split("/")
            if(valuesToApplyOperation[1].toDouble() == 0.0){
                throw RuntimeException("Não pode dividir por zero")
            }
            return valuesToApplyOperation[0].toDouble() / valuesToApplyOperation[1].toDouble()
        }
    }
}