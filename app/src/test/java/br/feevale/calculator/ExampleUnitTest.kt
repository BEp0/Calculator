package br.feevale.calculator

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `testa a funcionalidade de aplicar o padrão correto da expressão passada`() {

        val expressao = "2.0 + 1.0 * 5"
        val lista = expressao.split(" ")
        if (lista.contains("*") || lista.contains("/")) {

            val valueToReplace = "?"

            println("realiza a multiplicação primeiro")

            val pattern = "\\d+(\\.\\d+)?(\\s*[\\/*]\\s*\\d+(\\.\\d+)?)+".toRegex()
            val matches = pattern.findAll(expressao)
            val replaced = expressao.replace(pattern, valueToReplace)

            val matchesResolved = matches.map { applyResult(it.value) }

            matchesResolved.forEach { println(it) }
            println(replaced)

            val replacedWithResults = replaceValues(replaced, matchesResolved.toList())
            println(replacedWithResults)

            val aplicarOperacoesSimples = aplicarOperacoesSimples(replacedWithResults)
            println(aplicarOperacoesSimples)

        } else {
            val aplicarOperacoesSimples = aplicarOperacoesSimples(expressao)
            println(aplicarOperacoesSimples)
        }

    }

    private fun aplicarOperacoesSimples(replacedWithResults: String): String{
        var response = 0.0
        var operation: Operation? = null
        val split = replacedWithResults.split(" ")
        split.forEach {
            if (!isOperation(it)){
                if (operation == null){
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

    private fun isOperation(value: String) = listOf("+", "-", "*", "/").contains(value)

    private fun replaceValues(expressao: String, valores: List<String>): String {
            var resultado = expressao
            valores.forEachIndexed { index, valor ->
                resultado = resultado.replaceFirst("?", valor)
            }
            return resultado
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

    private fun getOperation(it: String): Operation? = when (it) {
        "+" -> Operation.SUM
        "-" -> Operation.SUB
        "/" -> Operation.DIV
        "*" -> Operation.MULT
        else -> null
    }
}