package doge.data.value

import doge.data.question.SymbolType

class BooleanValue(var value: Boolean) : BaseSymbolValue(SymbolType.BOOLEAN) {

    constructor(value: String) : this(value.toBoolean())

    override fun and(that: BaseSymbolValue): BooleanValue = when (that) {
        is BooleanValue -> BooleanValue(value && that.value)
        else -> super.and(that)
    }

    override fun or(that: BaseSymbolValue): BooleanValue = when (that) {
        is BooleanValue -> BooleanValue(value || that.value)
        else -> super.or(that)
    }

    override fun equals(other: Any?): Boolean = when (other) {
        is BooleanValue -> value == other.value
        else -> super.equals(other)
    }

    override fun not(): BaseSymbolValue = BooleanValue(!value)

}
