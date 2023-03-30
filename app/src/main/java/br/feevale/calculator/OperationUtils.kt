package br.feevale.calculator

class OperationUtils {

    companion object{
        fun getOperation(it: String): Operation? = when (it) {
            "+" -> Operation.SUM
            "-" -> Operation.SUB
            "/" -> Operation.DIV
            "*" -> Operation.MULT
            else -> null
        }
    }
}