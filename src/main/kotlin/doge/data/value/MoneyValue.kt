package doge.data.value

import java.math.BigDecimal

class MoneyValue(value: BigDecimal) : DecimalValue(value) {

    constructor(value: String) : this(BigDecimal(value))
    constructor(value: Int) : this(BigDecimal(value))

}