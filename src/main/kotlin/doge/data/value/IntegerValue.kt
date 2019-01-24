package doge.data.value

import doge.data.question.SymbolType
import java.math.BigDecimal

class IntegerValue(var value: Int) : BaseSymbolValue(SymbolType.INTEGER) {

    constructor(value: String) : this(value.toInt())

    override fun plus(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is IntegerValue -> IntegerValue(value + that.value)
        else -> super.plus(that)
    }

    override fun minus(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is IntegerValue -> IntegerValue(value - that.value)
        else -> super.minus(that)
    }

    override fun times(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is IntegerValue -> IntegerValue(value * that.value)
        else -> super.times(that)
    }

    override fun div(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is IntegerValue -> IntegerValue(value / that.value)
        else -> super.div(that)
    }

    override fun equals(other: Any?): Boolean = when (other) {
        is IntegerValue -> value == other.value
        else -> super.equals(other)
    }

    override fun compareTo(other: BaseSymbolValue): Int = when (other) {
        is IntegerValue -> value.compareTo(other.value)
        else -> super.compareTo(other)
    }

    override fun castTo(that: SymbolType): BaseSymbolValue? = when (that) {
        SymbolType.DECIMAL -> DecimalValue(BigDecimal(value))
        SymbolType.MONEY -> MoneyValue(BigDecimal(value))
        else -> super.castTo(that)
    }

}
