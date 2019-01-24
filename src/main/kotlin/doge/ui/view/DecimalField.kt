package doge.ui.view

import doge.ui.model.DecimalViewModel
import tornadofx.bind

class DecimalField(question: DecimalViewModel) : QuestionTextField(question) {
    init {
        with(root) {
            stripNonNumeric()
            bind(question.decimalProperty)
        }
    }
}