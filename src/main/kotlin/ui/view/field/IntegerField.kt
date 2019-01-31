package ui.view.field

import javafx.scene.control.TextInputControl
import tornadofx.bind
import tornadofx.mutateOnChange
import ui.model.viewmodel.IntegerViewModel

class IntegerField(question: IntegerViewModel) : QuestionTextField(question) {

    init {
        with(root) {
            stripNonInteger()
            bind(question.integerProperty)
            attachListener(textProperty())
        }
    }

    private fun TextInputControl.stripNonInteger() = textProperty().mutateOnChange { it?.replace(Regex("[^0-9\\-]"), "") }

}