package ui.view.field

import tornadofx.bind
import ui.model.viewmodel.DecimalViewModel

class DecimalField(question: DecimalViewModel) : QuestionTextField(question) {

    init {
        with(root) {
            stripNonNumeric()
            bind(question.decimalProperty)
            attachListener(textProperty())
        }
    }
}