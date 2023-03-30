package br.feevale.calculator

import br.feevale.calculator.OperationUtils.Companion.getOperation

class Calculate {

    fun calculate(expression: String): String{

        val lista = expression.split(" ")
        if (lista.contains("*") || lista.contains("/")) {

            val valueToReplace = "?"

            println("realiza a multiplicação primeiro")

            val pattern = "\\d+(\\.\\d+)?(\\s*[\\/*]\\s*\\d+(\\.\\d+)?)+".toRegex()
            val matches = pattern.findAll(expression)
            val replaced = expression.replace(pattern, valueToReplace)

            val matchesResolved = matches.map { applyResult(it.value) }

            matchesResolved.forEach { println(it) }
            println(replaced)

            val replacedWithResults = replaceValues(replaced, matchesResolved.toList())
            println(replacedWithResults)

            val aplicarOperacoesSimples = aplicarOperacoesSimples(replacedWithResults)
            println(aplicarOperacoesSimples)

            return aplicarOperacoesSimples

        }

        val aplicarOperacoesSimples = aplicarOperacoesSimples(expression)
        println(aplicarOperacoesSimples)

        return aplicarOperacoesSimples
    }


    private fun isOperation(value: String) = listOf("+", "-", "*", "/").contains(value)

    private fun replaceValues(expressao: String, valores: List<String>): String {
        var resultado = expressao
        valores.forEachIndexed { index, valor ->
            resultado = resultado.replaceFirst("?", valor)
        }
        return resultado
    }

    private fun aplicarOperacoesSimples(replacedWithResults: String): String {
        var response = 0.0
        var operation: Operation? = null
        val split = replacedWithResults.split(" ")
        split.forEach {
            if (!isOperation(it)) {
                if (operation == null) {
                    response = response.plus(it.toDouble())
                } else {
                    response = operation!!.apply(response, it.toDouble())
                }
            } else {
                operation = getOperation(it)
            }
        }

        return response.toString()
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