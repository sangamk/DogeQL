package doge.ast.node.expression.visitor.evaluation

import doge.ast.node.expression.BinaryExpression
import doge.ast.node.expression.LiteralExpression
import doge.ast.node.expression.ReferenceExpression
import doge.ast.node.expression.UnaryExpression
import doge.ast.node.expression.visitor.ExpressionVisitor
import doge.data.symbol.SymbolTable
import doge.data.value.BaseSymbolValue


class EvaluationVisitor(private val symbolTable: SymbolTable) : ExpressionVisitor<BaseSymbolValue> {

    override fun visit(literal: LiteralExpression): BaseSymbolValue {
        return literal.value
    }

    override fun visit(reference: ReferenceExpression): BaseSymbolValue {
        symbolTable.findSymbol(reference.name)?.let {
            it.evaluate(symbolTable)
            return it.value
        } ?: run {
            throw NoSuchElementException("Unable to find reference ${reference.name}")
        }
    }

    override fun visit(unary: UnaryExpression): BaseSymbolValue {
        return unary.operation(unary.next.accept(this))
    }

    override fun visit(binary: BinaryExpression): BaseSymbolValue {
        return binary.operation(binary.left.accept(this), binary.right.accept(this))
    }

}