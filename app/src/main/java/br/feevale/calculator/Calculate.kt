package br.feevale.calculator

class Calculate {

    fun execute(expression: String): String {
        val operation = Operation()
        if (isMultOrDiv(expression)) {
            return operation.applyComplexOperation(expression)
        }
        return operation.applySimpleOperation(expression)
    }

    private fun isMultOrDiv(expression: String) =
        expression.contains("*") || expression.contains("/")
}