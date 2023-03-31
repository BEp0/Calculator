package br.feevale.calculator

import br.feevale.calculator.OperationUtils.Companion.getOperation
import br.feevale.calculator.OperationUtils.Companion.isMultOrDiv

class Calculate {

    fun execute(expression: String): String {
        val operationUtils = OperationUtils()
        if (isMultOrDiv(expression)) {
            return operationUtils.applyComplexOperation(expression)
        }
        return operationUtils.applySimpleOperation(expression)
    }
}