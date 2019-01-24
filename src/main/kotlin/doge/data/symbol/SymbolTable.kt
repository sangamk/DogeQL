package doge.data.symbol

import doge.ast.node.expression.Expression
import doge.common.Name
import doge.data.question.SymbolType
import doge.data.value.BaseSymbolValue

class SymbolTable {

    private var symbols = HashMap<Name, Symbol>()

    companion object {
        private const val internalNamePrefix = "@__VAR__"
    }

    private var internalNameIndex = 0

    fun evaluateTable() {
        symbols.forEach { _, symbol ->
            if (symbol.expression != null) {
                symbol.evaluate(this)
            }
        }
    }

    fun registerSymbol(type: SymbolType, value: Expression? = null): SymbolRegistrationResult {
        val name = nextFreeInternalName()
        return registerSymbol(name, type, value)
    }

    fun registerSymbol(name: Name, type: SymbolType, value: Expression? = null): SymbolRegistrationResult {
        val previousSymbol = findSymbol(name)

        return when {
            previousSymbol == null -> {
                val symbol = Symbol(type, value)
                symbols[name] = symbol
                SymbolRegistrationResult.Registered(name, symbol)
            }
            previousSymbol.value.type == type -> {
                SymbolRegistrationResult.AlreadyRegistered(name)
            }
            else -> SymbolRegistrationResult.AlreadyRegisteredTypeMismatch(name)
        }
    }

    fun assign(name: String, value: BaseSymbolValue) {
        findSymbol(name)?.assign(value) ?: run {
            throw NoSuchElementException("Unable to find $name in symbol table")
        }
    }

    fun assign(name: String, type: SymbolType, value: Expression) {
        findSymbol(name)?.let {
            symbols[name] = Symbol(type, value)
        } ?: run {
            throw NoSuchElementException("Unable to find $name in symbol table")
        }
    }

    fun findSymbol(name: Name): Symbol? = symbols[name]

    private fun nextFreeInternalName(): Name {
        val name = internalNamePrefix + internalNameIndex

        ++internalNameIndex

        return name
    }

}