package doge.ast.node.expression.operation

import doge.data.question.SymbolType
import doge.data.value.BaseSymbolValue
import javax.naming.OperationNotSupportedException

enum class UnaryOperation(private val operation: (input: BaseSymbolValue) -> BaseSymbolValue) {

    NEGATE({ input ->
        !input
    });

    operator fun invoke(input: BaseSymbolValue): BaseSymbolValue {
        return operation(input)
    }

    fun getResolveType(type: SymbolType): SymbolType {
        val defaultValue = type.getDefaultInstance()

        return try {
            this(defaultValue).type
        } catch (_: OperationNotSupportedException) {
            SymbolType.UNDEFINED
        }
    }

    companion object {
        fun fromString(string: String) = when (string) {
            "!" -> UnaryOperation.NEGATE
            else -> throw TypeCastException("Unable to convert $string to unary operation")
        }
    }
}

