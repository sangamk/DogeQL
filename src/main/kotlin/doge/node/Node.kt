package doge.node

import doge.data.question.Question
import doge.data.symbol.SymbolTable
import doge.typechecker.pass.CircularDependencyPass
import doge.typechecker.pass.DuplicatePass
import doge.typechecker.pass.ScopePass
import doge.typechecker.pass.TypePass

abstract class Node(internal var symbolTable: SymbolTable) {
    internal val children = ArrayList<Node>()

    fun addChild(child: Node) {
        children.add(child)
    }

    open fun updateQuestion(question: Question) {
        symbolTable.assign(question.name, question.value)
        symbolTable.evaluateTable()
    }

    abstract fun getEnabledQuestions(): List<Question>

    abstract fun accept(pass: ScopePass)

    abstract fun accept(pass: DuplicatePass)

    abstract fun accept(pass: TypePass)

    abstract fun accept(pass: CircularDependencyPass)

}