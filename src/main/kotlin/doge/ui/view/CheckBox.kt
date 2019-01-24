package doge.ui.view

import doge.ui.model.BooleanViewModel
import javafx.scene.control.CheckBox
import tornadofx.bind

class CheckBox(question: BooleanViewModel) : QuestionField() {

    override val root = CheckBox()

    init {
        with(root){
            bind(question.booleanValue)

            selectedProperty().addListener({ _, _, _ ->
                question.update()
            })
        }
    }
}