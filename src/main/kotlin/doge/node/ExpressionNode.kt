package doge.node

import doge.ast.node.expression.SourceLocation
import doge.common.Name
import doge.data.question.Question
import doge.data.question.SymbolType
import doge.data.symbol.SymbolTable
import doge.typechecker.pass.CircularDependencyPass
import doge.typechecker.pass.DuplicatePass
import doge.typechecker.pass.ScopePass
import doge.typechecker.pass.TypePass

class ExpressionNode(symbolTable: SymbolTable, val reference: Name, val sourceLocation: SourceLocation)
    : Node(symbolTable) {

    override fun getEnabledQuestions(): List<Question> {

        val symbol = symbolTable.findSymbol(reference)

        if (symbol == null) {
            throw IllegalStateException("TODO")
        }

        val castedValue = symbol.value.castTo(SymbolType.BOOLEAN)
                ?: throw IllegalStateException("Unable to cast to boolean") // TODO: better exception

        if (castedValue.booleanValue.value) {
            return children.flatMap { child ->
                child.getEnabledQuestions()
            }
        }
        return listOf()
    }

    override fun accept(pass: ScopePass) {
        pass.visit(this)
    }

    override fun accept(pass: DuplicatePass) {
        pass.visit(this)
    }

    override fun accept(pass: TypePass) {
        pass.visit(this)
    }

    override fun accept(pass: CircularDependencyPass) {
        pass.visit(this)
    }
}