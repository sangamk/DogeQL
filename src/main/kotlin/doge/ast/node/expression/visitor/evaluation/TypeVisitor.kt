package doge.ast.node.expression.visitor.evaluation

import doge.ast.node.expression.BinaryExpression
import doge.ast.node.expression.LiteralExpression
import doge.ast.node.expression.ReferenceExpression
import doge.ast.node.expression.UnaryExpression
import doge.ast.node.expression.visitor.ExpressionVisitor
import doge.data.question.SymbolType
import doge.data.symbol.SymbolTable


class TypeVisitor(private val symbolTable: SymbolTable) : ExpressionVisitor<SymbolType> {

    override fun visit(literal: LiteralExpression): SymbolType {
        return literal.value.type
    }

    override fun visit(reference: ReferenceExpression): SymbolType {
//        if (reference.type != SymbolType.UNDEFINED) {
//            return reference.type
//        }
//
//        val symbol = symbolTable.findSymbol(reference.name)
//
//        if (symbol == null) {
//            throw IllegalStateException("No symbol registered for reference ${reference.name}")
//        }
//
//        if (symbol.expression == null) {
//            return symbol.value.type
//        } else {
//            val resolvedType = symbol.expression.accept(this)
//
//            reference.type = resolvedType
//
//            return resolvedType
//        }
        TODO("Fix")
    }

    override fun visit(unary: UnaryExpression): SymbolType {
        val nextResolvedType = unary.next.accept(this)

        if (nextResolvedType == SymbolType.UNDEFINED) {
            return SymbolType.UNDEFINED
        }

        return unary.operation.getResolveType(nextResolvedType)
    }

    override fun visit(binary: BinaryExpression): SymbolType {
        val leftResolvedType = binary.left.accept(this)
        val rightResolvedType = binary.right.accept(this)

        if (leftResolvedType == SymbolType.UNDEFINED || rightResolvedType == SymbolType.UNDEFINED) {
            return SymbolType.UNDEFINED
        }

        return binary.operation.getResolvedType(leftResolvedType, rightResolvedType)
    }

}