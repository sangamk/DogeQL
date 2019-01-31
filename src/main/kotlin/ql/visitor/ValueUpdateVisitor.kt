package ql.visitor

import ql.ast.node.Block
import ql.ast.node.Form
import ql.ast.node.expression.BinaryExpression
import ql.ast.node.expression.LiteralExpression
import ql.ast.node.expression.ReferenceExpression
import ql.ast.node.expression.UnaryExpression
import ql.ast.node.statement.IfStatement
import ql.ast.node.statement.QuestionStatement
import ql.data.symbol.SymbolTable
import ql.data.value.BaseSymbolValue

class ValueUpdateVisitor(
        private val referenceProvider: (reference: String) -> BaseSymbolValue,
        private val referenceUpdater: (reference: String, value: BaseSymbolValue) -> Unit
) : QuestionnaireASTBaseVisitor<Unit> {

    companion object {
        fun default(symbolTable: SymbolTable): ValueUpdateVisitor {
            return ValueUpdateVisitor(
                    { reference ->
                        symbolTable.findSymbol(reference)
                                ?: throw IllegalStateException("Unable to find reference $reference")
                    },
                    { name, value ->
                        symbolTable.assign(name, value)
                    }
            )
        }
    }

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
        questionStatement.expression?.let {
            referenceUpdater(questionStatement.name.text, EvaluationVisitor(referenceProvider).visit(it))
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