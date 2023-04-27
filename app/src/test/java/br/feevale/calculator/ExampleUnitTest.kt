package br.feevale.calculator

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val operation = Operation()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `testa multiplicação com ponto flutuante`() {
        val expected = "8.8"
        val expression = "4 * 2.2"
        val response = operation.execute(expression)
        assertEquals(expected, response)
    }

    @Test
    fun `testa subtração com resultado negativo`() {
        val expected = "-2"
        val expression = "2 - 4"
        val response = operation.execute(expression)
        assertEquals(expected, response)
    }

    @Test
    fun `testa soma com ponto flutuante`() {
        val expected = "2.2"
        val expression = "2 + 0.2"
        val response = operation.execute(expression)
        assertEquals(expected, response)
    }

    @Test
    fun `testa divisão por zero`() {
        val expected = "Não pode dividir por zero"
        val expression = "2 / 0"

        val exception = assertThrows(RuntimeException::class.java) {
            operation.execute(expression)
        }

        assertEquals(expected, exception.message)
    }
}