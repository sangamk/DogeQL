package ql.ast.node.expression

import ql.ast.location.SourceLocation
import ql.ast.node.expression.operation.BinaryOperation
import ql.visitor.QuestionnaireASTBaseVisitor

class BinaryExpression(
        val left: Expression,
        val right: Expression,
        val operation: BinaryOperation,
        sourceLocation: SourceLocation)
    : Expression(sourceLocation) {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}