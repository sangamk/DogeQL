package lexer

import org.junit.Assert
import org.junit.Test

class StringLiteralTests {

    @Test
    fun `quoted text should be parsed as string`() {
        val input = "\"Hello\""
        val result = ParserHost(input).parser.literal().LIT_STRING()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `quoted text with whitespace should be parsed as string`() {
        val input = "\"Hello World\""
        val result = ParserHost(input).parser.literal().LIT_STRING()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `quoted empty text should e parsed as string`() {
        val input = "\"\""
        val result = ParserHost(input).parser.literal().LIT_STRING()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `quoted whitespace should be parsed as string`() {
        val input = "\"                    \""
        val result = ParserHost(input).parser.literal().LIT_STRING()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `quoted integer should be parsed as string`() {
        val input = "\"1\""
        val result = ParserHost(input).parser.literal().LIT_STRING()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `mismatched quotes should not parse`() {
        val input = "\""
        val result = ParserHost(input).parser.literal().LIT_STRING()
        Assert.assertNull(result)
    }

    @Test
    fun `no quotes should not parse`() {
        val input = "Hello"
        val result = ParserHost(input, expectError = true).parser.literal().LIT_STRING()
        Assert.assertNull(result)
    }

}
