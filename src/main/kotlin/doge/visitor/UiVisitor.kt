package doge.visitor

import doge.ast.node.Block
import doge.ast.node.Form
import doge.ast.node.IfStatement
import doge.ast.node.QuestionStatement
import doge.ast.node.expression.*
import doge.data.question.Question

class UiVisitor : QuestionnaireASTBaseVisitor<List<Question>> {

    override fun visit(form: Form): List<Question> {
        return visit(form.block)
    }

    override fun visit(block: Block): List<Question> {
        return block.statements.flatMap { statement ->
            visit(statement)
        }.toList()
    }

    override fun visit(ifStatement: IfStatement): List<Question> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(questionStatement: QuestionStatement): List<Question> {
        val name = questionStatement.name
        val label = questionStatement.label
        val type = questionStatement.type

        val location = SourceLocation(0, 0, 0, 0)

        return listOf(Question(name, label, type.getDefaultInstance(), location, location, false))
    }

    override fun visit(binaryExpression: BinaryExpression): List<Question> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(unaryExpression: UnaryExpression): List<Question> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(referenceExpression: ReferenceExpression): List<Question> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(literalExpression: LiteralExpression): List<Question> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}