package ui.controller

import ql.ast.DogeParser
import ql.ast.node.QLNode
import ql.data.symbol.SymbolTable
import ql.visitor.ValueUpdateVisitor
import qls.ast.QlsParser
import qls.ast.node.QlsNode
import tornadofx.Controller
import tornadofx.observable
import ui.model.domain.Question
import ui.visitor.QuestionVisitor
import java.io.File

class DogeController : Controller() {

    private var symbolTable: SymbolTable? = null
    private var ast: QLNode? = null

    var infoMessages = mutableListOf<String>().observable()
    var questions = mutableListOf<Question>().observable()
    var style: QlsNode? = null

    fun loadQuestionnaire(file: File) {
        val parseResult = DogeParser().parse(file)

        parseResult?.let {
            symbolTable = it.symbolTable
            ast = it.ast

            addInfoMessages(it.info)
        }

        reloadQuestions()
    }

    private fun addInfoMessages(info: List<String>) {
        infoMessages.removeAll()
        infoMessages.addAll(info)
    }

    fun loadStyle(file: File) {
        style = QlsParser().parse(file)
    }

    fun reloadQuestions() {
        if (infoMessages.isEmpty()) {
            symbolTable.let {
                val visitor = QuestionVisitor(symbolTable!!)
                val enabledQuestions = ast!!.accept(visitor)
                updateQuestions(enabledQuestions)
            }
        }
    }

    fun evaluate(question: Question) {
        symbolTable?.let {
            it.assign(question.name, question.value)
            ast?.accept(ValueUpdateVisitor.default(it))
        }
    }

    fun hasQuestion(name: String): Boolean {
        return questions.any { it.name == name }
    }

    fun getQuestion(name: String): Question {
        return questions.first { it.name == name }
    }

    // Replacing observable list q  with new list will break observable binding
    // That is why we update internal values
    private fun updateQuestions(newDataQuestions: List<Question>) {

        val toAdd = newDataQuestions.filter { question ->
            question !in questions || question.readOnly
        }

        questions.removeIf { question ->
            question !in newDataQuestions || question.readOnly
        }

        toAdd.forEach { question ->
            questions.add(newDataQuestions.indexOf(question), question)
        }

    }

}