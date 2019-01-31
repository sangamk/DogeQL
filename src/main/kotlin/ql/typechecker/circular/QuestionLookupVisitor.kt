package ql.typechecker.circular

import ql.ast.node.Block
import ql.ast.node.Form
import ql.ast.node.expression.BinaryExpression
import ql.ast.node.expression.LiteralExpression
import ql.ast.node.expression.ReferenceExpression
import ql.ast.node.expression.UnaryExpression
import ql.ast.node.statement.IfStatement
import ql.ast.node.statement.QuestionStatement
import ql.visitor.QuestionnaireASTBaseVisitor

class QuestionLookupVisitor(val reference: String) : QuestionnaireASTBaseVisitor<QuestionStatement?> {

    override fun visit(form: Form): QuestionStatement? {
        return visit(form.block)
    }

    override fun visit(block: Block): QuestionStatement? {
        block.statements.forEach {
            visit(it)?.let {
                return it
            }
        }

        return null
    }

    override fun visit(ifStatement: IfStatement): QuestionStatement? {
        return visit(ifStatement.block)
    }

    override fun visit(questionStatement: QuestionStatement): QuestionStatement? {
        return when {
            questionStatement.name.text == reference -> questionStatement
            questionStatement.expression != null -> visit(questionStatement.expression)
            else -> null
        }
    }

    override fun visit(binaryExpression: BinaryExpression): QuestionStatement? {
        return null
    }

    override fun visit(unaryExpression: UnaryExpression): QuestionStatement? {
        return null
    }

    override fun visit(referenceExpression: ReferenceExpression): QuestionStatement? {
        return null
    }

    override fun visit(literalExpression: LiteralExpression): QuestionStatement? {
        return null
    }

}
