package doge.data.value

import doge.data.question.SymbolType
import javax.naming.OperationNotSupportedException

abstract class BaseSymbolValue(val type: SymbolType) : Comparable<BaseSymbolValue> {

    open infix fun and(that: BaseSymbolValue): BooleanValue {
        return attemptOperator(that, "&&", { left, right -> left and right })
    }

    open infix fun or(that: BaseSymbolValue): BooleanValue {
        return attemptOperator(that, "||", { left, right -> left or right })
    }

    open operator fun plus(that: BaseSymbolValue): BaseSymbolValue {
        return attemptOperator(that, "+", { left, right -> left + right })
    }

    open operator fun minus(that: BaseSymbolValue): BaseSymbolValue {
        return attemptOperator(that, "-", { left, right -> left - right })
    }

    open operator fun times(that: BaseSymbolValue): BaseSymbolValue {
        return attemptOperator(that, "*", { left, right -> left * right })
    }

    open operator fun div(that: BaseSymbolValue): BaseSymbolValue {
        return attemptOperator(that, "/", { left, right -> left / right })
    }

    open operator fun not(): BaseSymbolValue {
        unsupportedOperation("!")
    }

    override fun compareTo(other: BaseSymbolValue): Int {
        return attemptOperator(other, "compareTo", { left, right -> left.compareTo(right) })
    }

    open fun castTo(that: SymbolType): BaseSymbolValue? = when (that) {
        type -> this
        else -> null
    }

    val integerValue: IntegerValue
        get() = this as IntegerValue

    val booleanValue: BooleanValue
        get() = this as BooleanValue

    val decimalValue: DecimalValue
        get() = this as DecimalValue

    val stringValue: StringValue
        get() = this as StringValue

    val moneyValue: MoneyValue
        get() = this as MoneyValue

    private fun unsupportedOperation(operator: String, that: BaseSymbolValue? = null): Nothing {
        that?.let {
            throw OperationNotSupportedException("Unable to apply '$operator' to $type and ${that.type}")
        } ?: run {
            throw OperationNotSupportedException("Unable to apply '$operator' to $type")
        }
    }

    private inline fun <O> attemptOperator(
            that: BaseSymbolValue,
            operatorString: String,
            operator: (left: BaseSymbolValue, right: BaseSymbolValue) -> O
    ): O {
        if (type == that.type) {
            unsupportedOperation(operatorString, that)
        }

        return castAndRetry(that, operatorString, operator)
    }

    private inline fun <O> castAndRetry(
            that: BaseSymbolValue,
            operatorString: String,
            operator: (left: BaseSymbolValue, right: BaseSymbolValue) -> O
    ): O {
        val castedThis = castTo(that.type)
        val castedThat = that.castTo(type)

        return when {
            castedThis != null && castedThat == null -> operator(castedThis, that)
            castedThis == null && castedThat != null -> operator(this, castedThat)
            else -> unsupportedOperation(operatorString, that)
        }
    }

}
