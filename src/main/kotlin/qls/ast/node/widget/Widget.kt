package qls.ast.node.widget

import qls.ast.node.attribute.Attribute
import qls.visitor.QlsVisitor

class Widget(val type: WidgetType) : Attribute {
    override fun <T> accept(visitor: QlsVisitor<T>): T {
        return visitor.visit(this)
    }
}