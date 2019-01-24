package doge.ast.node.expression

import doge.data.value.IntegerValue
import org.junit.Assert
import org.junit.Test
import javax.naming.OperationNotSupportedException

class IntegerExpressionTests {

    @Test
    fun `integer addition works as expected`() {
        val result = IntegerValue(10) + IntegerValue(5)
        Assert.assertEquals(15, result.integerValue.value)
    }

    @Test
    fun `integer subtraction works as expected`() {
        val result = IntegerValue(10) - IntegerValue(5)
        Assert.assertEquals(5, result.integerValue.value)
    }

    @Test
    fun `integer multiplication works as expected`() {
        val result = IntegerValue(10) * IntegerValue(5)
        Assert.assertEquals(50, result.integerValue.value)
    }

    @Test
    fun `integer division works as expected`() {
        val result = IntegerValue(10) / IntegerValue(5)
        Assert.assertEquals(2, result.integerValue.value)
    }

    @Test
    fun `integer equality works as expected`() {
        val result = IntegerValue(10) == IntegerValue(10)
        Assert.assertTrue(result)
    }

    @Test
    fun `integer inequality works as expected`() {
        val result = IntegerValue(10) != IntegerValue(5)
        Assert.assertTrue(result)
    }

    @Test
    fun `integer equality is the inverse of integer inequality`() {
        val resultTrue = IntegerValue(10) == IntegerValue(10)
        val resultFalse = IntegerValue(10) != IntegerValue(10)
        Assert.assertTrue(resultTrue)
        Assert.assertFalse(resultFalse)
    }

    @Test
    fun `integer greater than comparison works as expected`() {
        val result = IntegerValue(10) > IntegerValue(5)
        Assert.assertTrue(result)
    }

    @Test
    fun `integer less than expression works as expected`() {
        val result = IntegerValue(5) < IntegerValue(10)
        Assert.assertTrue(result)
    }

    @Test
    fun `integer greater than and less than are mutually exclusive`() {
        val resultTrue = IntegerValue(10) > IntegerValue(5)
        Assert.assertTrue(resultTrue)

        val resultFalse = IntegerValue(10) < IntegerValue(5)
        Assert.assertFalse(resultFalse)
    }

    @Test
    fun `integer greater than or equals works as expected`() {
        val result1 = IntegerValue(10) >= IntegerValue(5)
        Assert.assertTrue(result1)

        val result2 = IntegerValue(10) >= IntegerValue(10)
        Assert.assertTrue(result2)
    }

    @Test
    fun `integer less than or equals works as expected`() {
        val result1 = IntegerValue(5) <= IntegerValue(10)
        Assert.assertTrue(result1)

        val result2 = IntegerValue(10) <= IntegerValue(10)
        Assert.assertTrue(result2)
    }

    @Test(expected = OperationNotSupportedException::class)
    fun `integer negation is not supported`() {
        !IntegerValue(1)
    }

}
