package doge.visitor

import doge.ast.node.Block
import doge.ast.node.Form
import doge.ast.node.IfStatement
import doge.ast.node.QuestionStatement
import doge.ast.node.expression.BinaryExpression
import doge.ast.node.expression.LiteralExpression
import doge.ast.node.expression.ReferenceExpression
import doge.ast.node.expression.UnaryExpression

class DuplicateQuestionVisitor : QuestionnaireASTBaseVisitor<Unit> {

    private val labels = HashSet<String>()
    private val names = HashSet<String>()

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
        val label = questionStatement.label
        if (!labels.add(label)) {
            // TODO()
        }

        val name = questionStatement.name
        if (!names.add(name)) {
            // TODO()
        }
    }

    override fun visit(binaryExpression: BinaryExpression) {

    }

    override fun visit(unaryExpression: UnaryExpression) {

    }

    override fun visit(referenceExpression: ReferenceExpression) {

    }

    override fun visit(literalExpression: LiteralExpression) {

    }

}