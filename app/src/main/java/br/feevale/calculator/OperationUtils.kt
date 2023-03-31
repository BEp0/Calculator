package br.feevale.calculator

class OperationUtils {

    companion object {
        fun isOperation(value: String) = listOf("+", "-", "*", "/").contains(value.trim())
        fun getOperation(it: String): Operation? = when (it) {
            "+" -> Operation.SUM
            "-" -> Operation.SUB
            "/" -> Operation.DIV
            "*" -> Operation.MULT
            else -> null
        }

        fun isMultOrDiv(expression: String) =
            expression.contains("*") || expression.contains("/")
    }

    private fun mountExpression(expression: String, values: List<String>): String {
        var expressionMounted = expression
        values.forEachIndexed { index, valor ->
            expressionMounted = expressionMounted.replaceFirst("?", valor)
        }
        return expressionMounted
    }

    private fun applyMultOrDivResult(expression: String): String {
        return if (expression.contains(Operation.MULT.type)){
            Operation.MULT.apply(expression).toString()
        } else {
            Operation.DIV.apply(expression).toString()
        }
    }

    fun applyComplexOperation(expression: String): String {

        val valueToReplace = "?"
        val multiplyAndDivOperationsPattern =
            "\\d+(\\.\\d+)?(\\s*[\\/*]\\s*\\d+(\\.\\d+)?)+".toRegex()
        val multiplyAndDivOperations = multiplyAndDivOperationsPattern.findAll(expression)
        val expressionResolved = multiplyAndDivOperations.map { applyMultOrDivResult(it.value) }

        val expressionReplaced = expression.replace(multiplyAndDivOperationsPattern, valueToReplace)
        val expressionResponse = mountExpression(expressionReplaced, expressionResolved.toList())
        return applySimpleOperation(expressionResponse)
    }

    fun applySimpleOperation(expression: String): String {
        var response = 0.0
        var operation: Operation? = null
        val split = expression.split(" ")
        split.forEach {
            if (OperationUtils.isOperation(it)) {
                operation = getOperation(it)
            } else {
                if (operation == null) {
                    response = response.plus(it.toDouble())
                } else {
                    response = operation!!.apply(response, it.toDouble())
                }
            }
        }
        return response.toString()
    }
}