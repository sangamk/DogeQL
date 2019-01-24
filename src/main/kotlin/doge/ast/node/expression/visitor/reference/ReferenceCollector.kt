package doge.ast.node.expression.visitor.reference

import doge.ast.node.expression.BinaryExpression
import doge.ast.node.expression.LiteralExpression
import doge.ast.node.expression.ReferenceExpression
import doge.ast.node.expression.UnaryExpression
import doge.ast.node.expression.visitor.ExpressionVisitor

class ReferenceCollector : ExpressionVisitor<Unit> {

    val references = mutableListOf<ReferenceExpression>()

    override fun visit(literal: LiteralExpression) {

    }

    override fun visit(reference: ReferenceExpression) {
        references.add(reference)
    }

    override fun visit(unary: UnaryExpression) {
        unary.next.accept(this)
    }

    override fun visit(binary: BinaryExpression) {
        binary.left.accept(this)
        binary.right.accept(this)
    }

}