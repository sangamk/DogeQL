package lexer

import org.antlr.v4.runtime.tree.TerminalNode
import org.junit.Assert

class ParseAssert {

    companion object {
        fun assertNotNullAndEqual(input: String, node: TerminalNode) {
            Assert.assertNotNull(node)
            Assert.assertEquals(input, node.text)
        }
    }

}