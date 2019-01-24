package lexer

import org.junit.Assert
import org.junit.Test

class DateLiteralTests {

    @Test
    fun `dates are dd-mm-yyyy`() {
        val input = "01-02-1970"
        val result = ParserHost(input).parser.literal().LIT_DATE()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `dates are yyyy-mm-dd`() {
        val input = "1970-02-01"
        val result = ParserHost(input).parser.literal().LIT_DATE()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun `dates without day should not parse`() {
        val input = "02-1970"
        val result = ParserHost(input, expectError = true).parser.literal().LIT_DATE()
        Assert.assertNull(result)
    }

    @Test
    fun `dates without month should not parse`() {
        val input = "01-1970"
        val result = ParserHost(input, expectError = true).parser.literal().LIT_DATE()
        Assert.assertNull(result)
    }

    @Test
    fun `dates without year should not parse`() {
        val input = "01-02"
        val result = ParserHost(input, expectError = true).parser.literal().LIT_DATE()
        Assert.assertNull(result)
    }

}
