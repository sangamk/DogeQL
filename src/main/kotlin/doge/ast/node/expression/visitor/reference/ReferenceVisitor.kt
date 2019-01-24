package doge.ast.node.expression.visitor.reference

import doge.ast.node.expression.BinaryExpression
import doge.ast.node.expression.LiteralExpression
import doge.ast.node.expression.ReferenceExpression
import doge.ast.node.expression.UnaryExpression
import doge.ast.node.expression.visitor.ExpressionVisitor

class ReferenceVisitor : ExpressionVisitor<Boolean> {

    override fun visit(literal: LiteralExpression): Boolean {
        return true
    }

    override fun visit(reference: ReferenceExpression): Boolean {
        return false
    }

    override fun visit(unary: UnaryExpression): Boolean {
        return unary.next.accept(this)
    }

    override fun visit(binary: BinaryExpression): Boolean {
        return binary.left.accept(this) && binary.right.accept(this)
    }

}