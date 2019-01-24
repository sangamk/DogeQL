package doge.data.value

import doge.common.Color
import doge.data.question.SymbolType


class ColorValue(var value: Color) : BaseSymbolValue(SymbolType.COLOR) {

    constructor(value: String) : this(Color.fromString(value))

}