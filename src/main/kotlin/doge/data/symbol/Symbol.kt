package doge.data.symbol

import doge.ast.node.expression.Expression
import doge.data.question.SymbolType
import doge.data.value.BaseSymbolValue

class Symbol(type: SymbolType, val expression: Expression?) {

    var value: BaseSymbolValue = type.getDefaultInstance()

    fun evaluate(symbolTable: SymbolTable) {
        expression?.let {
            value = expression.evaluate(symbolTable)
        }
    }

    fun assign(value: BaseSymbolValue) {
        this.value = value
    }

}