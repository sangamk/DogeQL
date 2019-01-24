package doge.ast.node

import doge.visitor.QuestionnaireASTBaseVisitor

class Block(val statements: List<Statement>) : QLNode {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}