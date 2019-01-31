package ui.view.field

import tornadofx.bind
import ui.model.viewmodel.MoneyViewModel

class MoneyField(question: MoneyViewModel) : QuestionTextField(question) {

    init {
        with(root) {
            stripNonNumeric()
            bind(question.moneyProperty)
            attachListener(textProperty())
        }
    }
}