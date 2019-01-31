package ql.ast.node

import ql.ast.location.Identifier
import ql.ast.location.SourceLocation
import ql.visitor.QuestionnaireASTBaseVisitor

class Form(val name: Identifier, val block: Block, location: SourceLocation) : QLNode(location) {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}