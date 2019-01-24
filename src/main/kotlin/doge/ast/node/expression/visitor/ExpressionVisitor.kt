package doge.ast.node.expression.visitor

import doge.ast.node.expression.*


interface ExpressionVisitor<out O> {

    fun visit(expression: Expression): O {
        throw UnsupportedOperationException("Unable to visit doge.expression $expression")
    }

    fun visit(literal: LiteralExpression): O

    fun visit(reference: ReferenceExpression): O

    fun visit(unary: UnaryExpression): O

    fun visit(binary: BinaryExpression): O

}