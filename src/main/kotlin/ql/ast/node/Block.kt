package ql.ast.node

import ql.ast.location.SourceLocation
import ql.ast.node.statement.Statement
import ql.visitor.QuestionnaireASTBaseVisitor

class Block(val statements: List<Statement>, location: SourceLocation) : QLNode(location) {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}