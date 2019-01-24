package doge.ast.node

import doge.ast.node.expression.SourceLocation
import doge.visitor.QuestionnaireASTBaseVisitor

class Form(val name: Identifier, val block: Block, val location : SourceLocation) : QLNode {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}