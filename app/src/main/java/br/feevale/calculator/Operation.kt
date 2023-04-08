package br.feevale.calculator

class Operation {

    companion object {
        fun isOperation(value: String) = listOf("+", "-", "*", "/").contains(value.trim())
    }

    private fun getOperation(operationType: String): OperationEnum? =
        when (operationType) {
            OperationEnum.SUM.type -> OperationEnum.SUM
            OperationEnum.SUB.type -> OperationEnum.SUB
            OperationEnum.DIV.type -> OperationEnum.DIV
            OperationEnum.MULT.type -> OperationEnum.MULT
            else -> null
        }

    private fun mountExpression(expression: String, values: List<String>): String {
        var expressionMounted = expression
        values.forEachIndexed { index, valor ->
            expressionMounted = expressionMounted.replaceFirst("?", valor)
        }
        return expressionMounted
    }

    private fun applyMultOrDivResult(expression: String): String {
        return if (expression.contains(OperationEnum.MULT.type)) {
            OperationEnum.MULT.apply(expression).toString()
        } else {
            OperationEnum.DIV.apply(expression).toString()
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
        var operationEnum: OperationEnum? = null
        val split = expression.split(" ")
        split.forEach {
            if (isOperation(it)) {
                operationEnum = getOperation(it)
            } else {
                if (operationEnum == null) {
                    response = response.plus(it.toDouble())
                } else {
                    response = operationEnum!!.apply(response, it.toDouble())
                }
            }
        }
        return formatResponse(response.toString())
    }

    private fun formatResponse(response: String): String {
        return if (!response.contains(".0"))
            response
        else
            response.replace(".0", "")
    }
}