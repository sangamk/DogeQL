package ui.view.field

import javafx.scene.control.TextField
import javafx.scene.control.TextInputControl
import tornadofx.mutateOnChange
import ui.model.viewmodel.QuestionViewModel

abstract class QuestionTextField(question: QuestionViewModel) : QuestionField(question) {

    final override val root = TextField()

    init {
        with(root) {
            isDisable = question.readOnly
        }
    }

    internal fun TextInputControl.stripNonNumeric(vararg allowedChars: String = arrayOf(".", ",", "-")) = textProperty().mutateOnChange {
        it?.replace(Regex("[^0-9${allowedChars.joinToString("")}]"), "")
    }
}