package doge.data.question

import doge.ast.node.expression.SourceLocation
import doge.data.symbol.SymbolTable
import doge.data.value.BaseSymbolValue

data class Question(
        val name: String,
        val label: String,
        var value: BaseSymbolValue,
        val nameLocation: SourceLocation,
        val labelLocation: SourceLocation,
        val readOnly: Boolean
) {
    fun update(symbolTable: SymbolTable) {
        symbolTable.findSymbol(name)?.let {
            value = it.value
        } ?: run {
            throw IllegalStateException("TODO")
        }
    }
}