package doge.ui.view

import doge.ui.model.IntegerViewModel
import javafx.scene.control.TextInputControl
import tornadofx.bind
import tornadofx.mutateOnChange

class IntegerField(question: IntegerViewModel) : QuestionTextField(question) {

    init {
        with(root){
            stripNonInteger()
            bind(question.integerProperty)
        }
    }

    private fun TextInputControl.stripNonInteger() = textProperty().mutateOnChange { it?.replace(Regex("[^0-9\\-]"), "") }

}