package qls.ast.node

import qls.common.Name
import qls.visitor.QlsVisitor

data class StyleSheet(val pages: List<Page>, val name: Name) : QlsNode {
    override fun <T> accept(visitor: QlsVisitor<T>): T {
        return visitor.visit(this)
    }
}