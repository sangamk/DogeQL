package ui.view.field

import javafx.beans.property.Property
import tornadofx.View
import ui.model.viewmodel.QuestionViewModel

abstract class QuestionField(val question: QuestionViewModel) : View() {

    // Listener must be attached after binding model
    // Or else it will be called before the model is updated and validated
    fun <T> attachListener(textProperty: Property<T>) {
        textProperty.addListener({ _, _, _ ->
            question.update()
        })
    }
}