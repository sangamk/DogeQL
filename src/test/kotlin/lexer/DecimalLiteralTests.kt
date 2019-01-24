package lexer

import org.junit.Assert
import org.junit.Test

class DecimalLiteralTests {

    @Test
    fun `zero decimal should parse`() {
        val input = "0.0"
        val result = ParserHost(input).parser.literal().LIT_DECIMAL()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `leading zeros should parse`() {
        val input = "00000.0"
        val result = ParserHost(input).parser.literal().LIT_DECIMAL()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `trailing zeros should parse`() {
        val input = "0.00000"
        val result = ParserHost(input).parser.literal().LIT_DECIMAL()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `decimal needs a decimal point`() {
        val input = "0"
        val result = ParserHost(input, expectError = true).parser.literal().LIT_DECIMAL()
        Assert.assertNull(result)
    }

}
