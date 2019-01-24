package doge.visitor

import doge.ast.node.Block
import doge.ast.node.Form
import doge.ast.node.IfStatement
import doge.ast.node.QuestionStatement
import doge.ast.node.expression.BinaryExpression
import doge.ast.node.expression.LiteralExpression
import doge.ast.node.expression.ReferenceExpression
import doge.ast.node.expression.UnaryExpression

class CircularDependencyVisitor : QuestionnaireASTBaseVisitor<Unit> {

    override fun visit(form: Form) {
        visit(form.block)
    }

    override fun visit(block: Block) {
        block.statements.forEach { visit(it) }
    }

    override fun visit(ifStatement: IfStatement) {
        visit(ifStatement.block)
    }

    override fun visit(questionStatement: QuestionStatement) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(binaryExpression: BinaryExpression) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(unaryExpression: UnaryExpression) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(referenceExpression: ReferenceExpression) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(literalExpression: LiteralExpression) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}