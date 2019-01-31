package ui.visitor

import ql.ast.node.Block
import ql.ast.node.Form
import ql.ast.node.expression.BinaryExpression
import ql.ast.node.expression.LiteralExpression
import ql.ast.node.expression.ReferenceExpression
import ql.ast.node.expression.UnaryExpression
import ql.ast.node.statement.IfStatement
import ql.ast.node.statement.QuestionStatement
import ql.data.symbol.SymbolTable
import ql.visitor.EvaluationVisitor
import ql.visitor.QuestionnaireASTBaseVisitor
import ui.model.domain.Question

class QuestionVisitor(private val symbolTable: SymbolTable) : QuestionnaireASTBaseVisitor<List<Question>> {

    private var visible = true

    override fun visit(form: Form): List<Question> {
        return form.block.accept(this)
    }

    override fun visit(block: Block): List<Question> {
        return block.statements.flatMap { statement ->
            statement.accept(this)
        }.toList()
    }

    override fun visit(ifStatement: IfStatement): List<Question> {
        val result = ifStatement.expression.accept(EvaluationVisitor.default(symbolTable))

        visible = result.booleanValue.value

        val ifBlock = ifStatement.block.accept(this)

        visible = true

        return ifBlock
    }

    override fun visit(questionStatement: QuestionStatement): List<Question> {
        val name = questionStatement.name
        val label = questionStatement.label
        var value = symbolTable.findSymbol(name.text)
        val readOnly = false

        if (questionStatement.expression != null) {
            value = questionStatement.expression.accept(EvaluationVisitor.default(symbolTable))
        }

        return listOf(Question(name.text, label.text, value!!, readOnly, visible))
    }

    override fun visit(binaryExpression: BinaryExpression): List<Question> {
        return listOf()
    }

    override fun visit(unaryExpression: UnaryExpression): List<Question> {
        return listOf()
    }

    override fun visit(referenceExpression: ReferenceExpression): List<Question> {
        return listOf()
    }

    override fun visit(literalExpression: LiteralExpression): List<Question> {
        return listOf()
    }
}