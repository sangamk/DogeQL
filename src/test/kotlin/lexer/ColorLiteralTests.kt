package lexer

import org.junit.Assert
import org.junit.Test

class ColorLiteralTests {

    @Test
    fun `6 hex digit color should be parsed as color`() {
        val input = "#123ABC"
        val result = ParserHost(input).parser.literal().LIT_COLOR()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `colors need leading # sign`() {
        val input = "123ABC"
        val result = ParserHost(input, expectError = true).parser.literal().LIT_COLOR()
        Assert.assertNull(result)
    }

    @Test
    fun `colors need hex digits`() {
        val input = "#"
        val result = ParserHost(input, expectError = true).parser.literal().LIT_COLOR()
        Assert.assertNull(result)
    }

}
