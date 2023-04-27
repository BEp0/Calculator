package br.feevale.calculator

class Operation {

    fun isOperation(value: String) = listOf("+", "-", "*", "/").contains(value.trim())

    fun execute(expression: String): String {
        val operation = getOperation(expression.split(" ")[1])
        val response = operation.apply(expression)
        return formatResponse(response.toString())
    }

    private fun getOperation(operationType: String): OperationEnum =
        when (operationType) {
            OperationEnum.SUM.type -> OperationEnum.SUM
            OperationEnum.SUB.type -> OperationEnum.SUB
            OperationEnum.DIV.type -> OperationEnum.DIV
            OperationEnum.MULT.type -> OperationEnum.MULT
            else -> throw RuntimeException()
        }

    private fun formatResponse(response: String): String {
        return if (!response.contains(".0")) response
        else response.replace(".0", "")
    }
}