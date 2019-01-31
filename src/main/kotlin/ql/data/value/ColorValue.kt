package ql.data.value

import ql.common.Color
import ql.data.symbol.SymbolType


class ColorValue(var value: Color) : BaseSymbolValue(SymbolType.COLOR) {

    constructor(value: String) : this(Color.fromString(value))

}