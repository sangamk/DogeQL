package qls.ast.node

import qls.common.Name
import qls.visitor.QlsVisitor

data class Section(val title: Name, val elements: List<Element>) : Element, Style {
    override fun <T> accept(visitor: QlsVisitor<T>): T {
        return visitor.visit(this)
    }
}