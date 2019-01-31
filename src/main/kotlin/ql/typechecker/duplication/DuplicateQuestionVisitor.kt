package ql.typechecker.duplication

import ql.ast.location.Identifier
import ql.ast.node.Block
import ql.ast.node.Form
import ql.ast.node.expression.BinaryExpression
import ql.ast.node.expression.LiteralExpression
import ql.ast.node.expression.ReferenceExpression
import ql.ast.node.expression.UnaryExpression
import ql.ast.node.statement.IfStatement
import ql.ast.node.statement.QuestionStatement
import ql.visitor.QuestionnaireASTBaseVisitor

class DuplicateQuestionVisitor(private val context: DuplicationErrorContext) : QuestionnaireASTBaseVisitor<Unit> {

    private val labels = mutableListOf<Identifier>()
    private val names = mutableListOf<Identifier>()

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
        labels.find { entry -> entry.text == label.text }?.let {
            context.labelDuplications += DuplicationError(label.text, it.location, label.location)
        } ?: run {
            labels += label
        }

        val name = questionStatement.name
        names.find { entry -> entry.text == name.text }?.let {
            context.nameDuplications += DuplicationError(name.text, it.location, name.location)
        } ?: run {
            names += name
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