package doge.ui.view

import doge.ui.model.MoneyViewModel
import tornadofx.bind

class MoneyField(question: MoneyViewModel) : QuestionTextField(question) {
    init {
        with(root){
            stripNonNumeric()
            bind(question.moneyProperty)
        }
    }
}