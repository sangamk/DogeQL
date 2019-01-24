package doge.ast.node

import doge.ast.node.expression.Expression
import doge.data.question.SymbolType
import doge.visitor.QuestionnaireASTBaseVisitor

class QuestionStatement(
        val label: String,
        val name: String,
        val type: SymbolType,
        val expression: Expression?
) : Statement {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}