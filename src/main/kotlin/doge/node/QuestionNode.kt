package doge.node

import doge.data.question.Question
import doge.data.symbol.SymbolTable
import doge.typechecker.pass.CircularDependencyPass
import doge.typechecker.pass.DuplicatePass
import doge.typechecker.pass.ScopePass
import doge.typechecker.pass.TypePass

class QuestionNode(symbolTable: SymbolTable, val question: Question) : Node(symbolTable) {

    override fun getEnabledQuestions(): List<Question> {
        question.update(symbolTable)

        return listOf(question)
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