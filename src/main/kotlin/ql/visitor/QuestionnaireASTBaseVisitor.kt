package ql.visitor

import ql.ast.node.Block
import ql.ast.node.Form
import ql.ast.node.QLNode
import ql.ast.node.expression.*
import ql.ast.node.statement.IfStatement
import ql.ast.node.statement.QuestionStatement
import ql.ast.node.statement.Statement

interface QuestionnaireASTBaseVisitor<out T> {

    fun visit(node: QLNode): T {
        return node.accept(this)
    }

    fun visit(expression: Expression): T {
        return expression.accept(this)
    }

    fun visit(form: Form): T

    fun visit(block: Block): T

    fun visit(statement: Statement): T {
        return statement.accept(this)
    }

    fun visit(ifStatement: IfStatement): T

    fun visit(questionStatement: QuestionStatement): T

    fun visit(binaryExpression: BinaryExpression): T

    fun visit(unaryExpression: UnaryExpression): T

    fun visit(referenceExpression: ReferenceExpression): T

    fun visit(literalExpression: LiteralExpression): T

}