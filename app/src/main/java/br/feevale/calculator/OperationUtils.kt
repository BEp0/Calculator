package br.feevale.calculator

class OperationUtils {

    companion object{
        fun isOperation(value: String) = listOf("+", "-", "*", "/").contains(value)
        fun isNotOperation(value: String) = !listOf("+", "-", "*", "/").contains(value)
        fun getOperation(it: String): Operation? = when (it) {
            "+" -> Operation.SUM
            "-" -> Operation.SUB
            "/" -> Operation.DIV
            "*" -> Operation.MULT
            else -> null
        }
    }
}