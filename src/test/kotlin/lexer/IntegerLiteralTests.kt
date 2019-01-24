package lexer

import org.junit.Assert
import org.junit.Test

class IntegerLiteralTests {

    @Test
    fun `zero should parse`() {
        val input = "0"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `multiple zeros should parse`() {
        val input = "00000"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `leading zeros should parse`() {
        val input = "000001"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `very large integer should parse`() {
        val input = "987654321987654321"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `negative integer should parse with sign`() {
        val input = "-1"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `negative zero integer should parse with sign`() {
        val input = "-0"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `very large negative integer should parse with sign`() {
        val input = "-98765432123456789"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `multiple min signs should parse`() {
        val input = "-----1"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `hexadecimal should parse`() {
        val input = "0x1"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `negative hexadecimal should parse`() {
        val input = "-0x1"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `zero hexadecimal should parse`() {
        val input = "0x0"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `fail should not be parsed as integer`() {
        val input = "fail"
        val result = ParserHost(input, expectError = true).parser.literal().LIT_INTEGER()
        Assert.assertNull(result)
    }

    @Test
    fun `decimal should not be parsed as integer`() {
        val input = "0.1"
        val result = ParserHost(input, expectError = true).parser.literal().LIT_INTEGER()
        Assert.assertNull(result)
    }

}
