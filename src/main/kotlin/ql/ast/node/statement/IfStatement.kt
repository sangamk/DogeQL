package ql.ast.node.statement

import ql.ast.location.SourceLocation
import ql.ast.node.Block
import ql.ast.node.expression.Expression
import ql.visitor.QuestionnaireASTBaseVisitor

class IfStatement(val expression: Expression, val block: Block, location: SourceLocation) : Statement(location) {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}