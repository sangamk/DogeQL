package ql.ast.node.expression

import ql.ast.location.Identifier
import ql.ast.location.SourceLocation
import ql.visitor.QuestionnaireASTBaseVisitor

class ReferenceExpression(val name: Identifier, sourceLocation: SourceLocation)
    : Expression(sourceLocation) {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}