package br.feevale.calculator

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import kotlin.math.exp

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
    fun `testa calculadora com as quatro operações`() {
        val calculate = Calculate()
        val expected = "5.0"
        val expression = "4 * 2 + 2 / 2 - 4"
        val response = calculate.execute(expression)
        assertEquals(expected, response)
    }

    @Test
    fun `testa calculadora com as soma e subtração`() {
        val calculate = Calculate()
        val expected = "0.0"
        val expression = "2 + 2 - 4"
        val response = calculate.execute(expression)
        assertEquals(expected, response)
    }

    @Test
    fun `testa ponto flutuante`() {
        val calculate = Calculate()
        val expected = "5.2"
        val expression = "2 + .2 + 3"
        val response = calculate.execute(expression)
        assertEquals(expected, response)
    }

    @Test
    fun `testa calculadora contra divisões por zero`() {
        val calculate = Calculate()
        val expected = "Não pode dividir por zero"
        val expression = "2 / 0"

        val exception = assertThrows(RuntimeException::class.java) {
            calculate.execute(expression)
        }

        assertEquals(expected, exception.message)
    }
}