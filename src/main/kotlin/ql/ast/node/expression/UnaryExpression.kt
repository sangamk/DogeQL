package ql.ast.node.expression

import ql.ast.location.SourceLocation
import ql.ast.node.expression.operation.UnaryOperation
import ql.visitor.QuestionnaireASTBaseVisitor

class UnaryExpression(val next: Expression, val operation: UnaryOperation, sourceLocation: SourceLocation)
    : Expression(sourceLocation) {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}