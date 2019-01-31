package ql.typechecker.scope

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

class ScopeVisitor(private val context: ScopeErrorContext) : QuestionnaireASTBaseVisitor<Unit> {

    private val definitions = ArrayDeque<Identifier>()
    private val references = ArrayDeque<Identifier>()

    override fun visit(form: Form) {
        visit(form.block)
    }

    override fun visit(block: Block) {
        val numDefinitions = definitions.size
        val numReferences = references.size

        block.statements.forEach { visit(it) }

        context.errors += references.filter { reference ->
            definitions.find { definition ->
                definition.text == reference.text
            } == null
        }

        while (definitions.size > numDefinitions) {
            definitions.pop()
        }

        while (references.size > numReferences) {
            references.pop()
        }
    }

    override fun visit(ifStatement: IfStatement) {
        visit(ifStatement.block)
        visit(ifStatement.expression)
    }

    override fun visit(questionStatement: QuestionStatement) {
        definitions.push(questionStatement.name)

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
        references.push(referenceExpression.name)
    }

    override fun visit(literalExpression: LiteralExpression) {

    }
}