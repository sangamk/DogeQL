package doge.ast.node.expression.visitor

interface ExpressionVisitable<in V: ExpressionVisitor<O>, out O> {

    fun accept(visitor: V): O

}