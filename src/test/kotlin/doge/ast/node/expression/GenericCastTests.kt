package doge.ast.node.expression

import doge.data.question.SymbolType
import doge.data.value.DecimalValue
import doge.data.value.IntegerValue
import org.amshove.kluent.`should be`
import org.junit.Test

class GenericCastTests {

    @Test
    fun `implicit typecasting promotes left side`() {
        val left = IntegerValue(1)
        val right = DecimalValue(2)
        val result = left + right

        result.type `should be` SymbolType.DECIMAL
    }

    @Test
    fun `implicit typecasting promotes right side`() {
        val left = DecimalValue(1)
        val right = IntegerValue(2)
        val result = left + right

        result.type `should be` SymbolType.DECIMAL
    }
}