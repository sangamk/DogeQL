package ql.data.value

import ql.data.symbol.SymbolType
import java.math.BigDecimal

class MoneyValue(value: BigDecimal) : DecimalValue(value) {

    constructor(value: Int) : this(BigDecimal(value))

    override fun castTo(that: SymbolType) = when (that) {
        SymbolType.DECIMAL -> DecimalValue(value)
        else -> super.castTo(that)
    }

}