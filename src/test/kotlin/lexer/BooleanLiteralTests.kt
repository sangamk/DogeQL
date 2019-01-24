package lexer

import org.junit.Assert
import org.junit.Test

class BooleanLiteralTests {

    @Test
    fun `true should be parsed as boolean`() {
        val input = "true"
        val result = ParserHost("true").parser.literal().LIT_BOOLEAN()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `false should be parsed as boolean`() {
        val input = "false"
        val result = ParserHost("false").parser.literal().LIT_BOOLEAN()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `fail should not be parsed as boolean`() {
        val input = "fail"
        val result = ParserHost("fail", expectError = true).parser.literal().LIT_BOOLEAN()
        Assert.assertNull(result)
    }

}
