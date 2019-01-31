package qls.visitor

import qls.ast.node.*
import qls.ast.node.attribute.Attribute
import qls.ast.node.attribute.DefaultAttributes


interface QlsVisitor<out T> {

    fun visit(styleSheet: StyleSheet): T
    fun visit(page: Page): T
    fun visit(style: Style): T
    fun visit(section: Section): T
    fun visit(defaultAttributes: DefaultAttributes): T
    fun visit(question: Question): T
    fun visit(element: Element): T
    fun visit(attributes: Attribute): T
}