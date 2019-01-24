package doge.ast.node.expression.operation

import doge.data.question.SymbolType
import doge.data.value.BaseSymbolValue
import doge.data.value.BooleanValue
import javax.naming.OperationNotSupportedException

enum class BinaryOperation(private val operation: (left: BaseSymbolValue, right: BaseSymbolValue) -> BaseSymbolValue) {

    GREATER({ left, right ->
        BooleanValue(left > right)
    }),

    LESS({ left, right ->
        BooleanValue(left < right)
    }),

    GREATEQUAL({ left, right ->
        BooleanValue(left >= right)
    }),

    LESSEQUAL({ left, right ->
        BooleanValue(left <= right)
    }),

    EQUAL({ left, right ->
        BooleanValue(left == right)
    }),

    NOTEQUAL({ left, right ->
        BooleanValue(left != right)
    }),

    AND({ left, right ->
        val boolTrue = BooleanValue(true)
        EQUAL(left, boolTrue) and EQUAL(right, boolTrue)
    }),

    OR({ left, right ->
        val boolTrue = BooleanValue(true)
        EQUAL(left, boolTrue) or (EQUAL(right, boolTrue))
    }),

    ADD({ left, right ->
        left + right
    }),

    SUBTRACT({ left, right ->
        left - right
    }),

    MULTIPLY({ left, right ->
        left * right
    }),

    DIVIDE({ left, right ->
        left / right
    });

    operator fun invoke(left: BaseSymbolValue, right: BaseSymbolValue): BaseSymbolValue {
        return operation(left, right)
    }

    fun getResolvedType(leftType: SymbolType, rightType: SymbolType): SymbolType {
        val leftDefaultValue = leftType.getDefaultInstance()
        val rightDefaultValue = rightType.getDefaultInstance()

        return try {
            this(leftDefaultValue, rightDefaultValue).type
        } catch (_: OperationNotSupportedException) {
            SymbolType.UNDEFINED
        }
    }

    companion object {
        fun fromString(string: String) = when (string) {
            ">" -> GREATER
            "<" -> LESS
            ">=" -> GREATEQUAL
            "<=" -> LESSEQUAL
            "==" -> EQUAL
            "!=" -> NOTEQUAL
            "&&" -> AND
            "||" -> OR
            "+" -> ADD
            "-" -> SUBTRACT
            "*" -> MULTIPLY
            "/" -> DIVIDE
            else -> throw TypeCastException("Unable to convert $string to binary operation")
        }
    }

}
