package ql.typechecker.circular

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
import java.util.*

class CircularDependencyVisitor(
        private val context: CircularDependencyErrorContext,
        private val requestQuestion: (reference: String) -> QuestionStatement?
) : QuestionnaireASTBaseVisitor<Unit> {
    private val visitedReferences = ArrayDeque<Identifier>()

    override fun visit(form: Form) {
        visit(form.block)
    }

    override fun visit(block: Block) {
        block.statements.forEach { visit(it) }
    }

    override fun visit(ifStatement: IfStatement) {
        visit(ifStatement.expression)
        visit(ifStatement.block)
    }

    override fun visit(questionStatement: QuestionStatement) {
        questionStatement.expression?.let {
            visit(it)
        }
    }

    override fun visit(binaryExpression: BinaryExpression) {
        visit(binaryExpression.left)
        visit(binaryExpression.right)
    }

    override fun visit(unaryExpression: UnaryExpression) {
        visit(unaryExpression.next)
    }

    override fun visit(referenceExpression: ReferenceExpression) {
        val reference = referenceExpression.name

        visitedReferences.find { it.text == reference.text }?.let {
            val chain = visitedReferences.clone().apply { push(reference) }.reversed()
            val first = chain.first()
            val error = CircularDependencyError(first, chain.drop(1))
            context.errors += error
            return
        }

        visitedReferences.push(referenceExpression.name)

        requestQuestion(reference.text)?.let {
            visit(it)
        }

        visitedReferences.pop()
    }

    override fun visit(literalExpression: LiteralExpression) {

    }
}

