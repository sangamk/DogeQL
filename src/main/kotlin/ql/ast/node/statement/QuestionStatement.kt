package ql.ast.node.statement

import ql.ast.location.Identifier
import ql.ast.location.SourceLocation
import ql.ast.location.Type
import ql.ast.node.expression.Expression
import ql.visitor.QuestionnaireASTBaseVisitor

class QuestionStatement(
        val label: Identifier,
        val name: Identifier,
        val type: Type,
        val expression: Expression?,
        location: SourceLocation
) : Statement(location) {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}