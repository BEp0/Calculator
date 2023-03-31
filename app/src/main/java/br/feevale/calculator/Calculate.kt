package br.feevale.calculator

import br.feevale.calculator.OperationUtils.Companion.getOperation

class Calculate {

    fun calculate(expression: String): String{
        val expressionSplited = expression.split(" ")

        if(isNotMultOrDiv(expressionSplited)) {
            return applySimpleOperation(expression)
        }

        return applyComplexOperation(expression)
    }

    private fun applyComplexOperation(expression: String): String {

        val valueToReplace = "?"
        val pattern = "\\d+(\\.\\d+)?(\\s*[\\/*]\\s*\\d+(\\.\\d+)?)+".toRegex()
        val matches = pattern.findAll(expression)
        val replaced = expression.replace(pattern, valueToReplace)
        val matchesResolved = matches.map { applyResult(it.value) }

        val replacedWithResults = replaceValues(replaced, matchesResolved.toList())
        return  applySimpleOperation(replacedWithResults)
    }

    private fun isNotMultOrDiv(expressionSplited: List<String>) = !(expressionSplited.contains("*") || expressionSplited.contains("/"))

    private fun replaceValues(expressao: String, valores: List<String>): String {
        var resultado = expressao
        valores.forEachIndexed { index, valor ->
            resultado = resultado.replaceFirst("?", valor)
        }
        return resultado
    }

    private fun applySimpleOperation(replacedWithResults: String): String {
        var response = 0.0
        var operation: Operation? = null
        val split = replacedWithResults.split(" ")
        split.forEach {
            if (OperationUtils.isOperation(it)) {
                operation = getOperation(it)
            } else {
                response = executeOperation(operation, response, it)
            }
        }

        return response.toString()
    }

    private fun executeOperation(
        operation: Operation?,
        response: Double,
        it: String
    ) = if (operation == null) {
        response.plus(it.toDouble())
    } else {
        operation!!.apply(response, it.toDouble())
    }

    private fun applyResult(expressao: String): String {
        if (expressao.contains(Operation.MULT.type)) {
            val respostaDaOperacao = Operation.MULT.apply(expressao)
            return respostaDaOperacao.toString()
        } else if (expressao.contains(Operation.DIV.type)) {
            val respostaDaOperacao = Operation.DIV.apply(expressao)
            return respostaDaOperacao.toString()
        }
        return ""
    }
}