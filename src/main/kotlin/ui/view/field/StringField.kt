package ui.view.field

import tornadofx.bind
import tornadofx.stripWhitespace
import ui.model.viewmodel.StringViewModel

class StringField(question: StringViewModel) : QuestionTextField(question) {
    init {
        with(root) {
            stripWhitespace()
            bind(question.stringProperty)
            attachListener(textProperty())
        }
    }
}