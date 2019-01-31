package qls.ast.node.attribute

import qls.common.Name
import qls.visitor.QlsVisitor

class AttributePair(val name: Name, val literal: String) : Attribute {
    override fun <T> accept(visitor: QlsVisitor<T>): T {
        return visitor.visit(this)
    }

}