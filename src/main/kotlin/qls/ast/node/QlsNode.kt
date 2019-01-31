package qls.ast.node

import qls.visitor.QlsVisitor

interface QlsNode {
    fun <T> accept(visitor: QlsVisitor<T>): T
}
