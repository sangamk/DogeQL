package ql.data.symbol

import ql.common.Color
import ql.data.value.*
import java.time.LocalDate

enum class SymbolType {
    UNDEFINED,
    BOOLEAN,
    INTEGER,
    DECIMAL,
    STRING,
    MONEY,
    COLOR,
    DATE;

    fun getDefaultInstance(): BaseSymbolValue = when (this) {
        UNDEFINED -> throw IllegalStateException("Unable to create symbol of undecided type")
        BOOLEAN -> BooleanValue(false)
        INTEGER -> IntegerValue(0)
        DECIMAL -> DecimalValue(0)
        STRING -> StringValue("")
        MONEY -> MoneyValue(0)
        COLOR -> ColorValue(Color(0, 0, 0, 0))
        DATE -> DateValue(LocalDate.now())
    }

}