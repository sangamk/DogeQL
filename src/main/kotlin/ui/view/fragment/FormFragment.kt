package ui.view.fragment

import javafx.geometry.Side
import tornadofx.*
import ui.controller.DogeController
import ui.model.viewmodel.ViewModelFactory
import ui.view.field.QuestionFieldFactory

class FormFragment : Fragment() {

    private val defaultPageName = "DogeQL"

    val model: DogeController by inject()

    override var root = drawer(Side.LEFT, false, false) {
        item(defaultPageName, expanded = true)
        {
            scrollpane {
                form {
                    fieldset {
                        children.bind(model.questions) {
                            field(it.label) {
                                isManaged = it.visible
                                isVisible = it.visible
                                val viewModel = ViewModelFactory().createQuestionViewModel(it)
                                add(QuestionFieldFactory().createQuestionField(viewModel))
                            }
                        }
                    }
                }

            }
        }
    }
}