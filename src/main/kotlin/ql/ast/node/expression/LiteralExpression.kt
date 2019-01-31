package ql.ast.node.expression

import ql.ast.location.SourceLocation
import ql.data.value.BaseSymbolValue
import ql.visitor.QuestionnaireASTBaseVisitor

class LiteralExpression(val value: BaseSymbolValue, sourceLocation: SourceLocation) : Expression(sourceLocation) {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}