package doge.ui.controller

import doge.ast.DogeParser
import doge.data.question.Question
import tornadofx.Controller

class DogeController: Controller() {

    private val tree = DogeParser().parse()

    fun getQuestions(): List<Question> {
        return tree
    }

    fun updateQuestion(question: Question){
    }
}