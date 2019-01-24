package doge.data.value

import doge.data.question.SymbolType
import java.math.BigDecimal

open class DecimalValue(var value: BigDecimal) : BaseSymbolValue(SymbolType.DECIMAL) {

    constructor(value: String): this(BigDecimal(value))
    constructor(value: Int): this(BigDecimal(value))

    override fun plus(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is DecimalValue -> DecimalValue(value + that.value)
        else -> super.plus(that)
    }

    override fun minus(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is DecimalValue -> DecimalValue(value - that.value)
        else -> super.minus(that)
    }

    override fun times(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is DecimalValue -> DecimalValue(value * that.value)
        else -> super.times(that)
    }

    override fun div(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is DecimalValue -> DecimalValue(value / that.value)
        else -> super.div(that)
    }

    override fun compareTo(other: BaseSymbolValue): Int = when (other) {
        is DecimalValue -> value.compareTo(other.value)
        else -> super.compareTo(other)
    }

}