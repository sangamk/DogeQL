package doge.ast.node

import doge.ast.node.expression.Expression
import doge.visitor.QuestionnaireASTBaseVisitor

class IfStatement(val expression: Expression, val block: Block) : Statement {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}