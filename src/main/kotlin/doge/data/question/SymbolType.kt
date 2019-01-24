package doge.data.question

import doge.common.Color
import doge.data.value.*
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
        SymbolType.UNDEFINED -> throw IllegalStateException("Unable to create symbol of undecided type")
        SymbolType.BOOLEAN -> BooleanValue(false)
        SymbolType.INTEGER -> IntegerValue(0)
        SymbolType.DECIMAL -> DecimalValue(0)
        SymbolType.STRING -> StringValue("")
        SymbolType.MONEY -> MoneyValue(0)
        SymbolType.COLOR -> ColorValue(Color(0, 0, 0, 0))
        SymbolType.DATE -> DateValue(LocalDate.now())
    }

}