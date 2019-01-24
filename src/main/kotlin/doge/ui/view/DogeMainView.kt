package doge.ui.view

import doge.ui.model.QuestionFormModel
import tornadofx.*


class DogeMainView : View() {

    private val model: QuestionFormModel by inject()

    override val root = scrollpane()

    private val minHeight = 400.0
    private val minWidth = 400.0

    init {
        root.minHeight = minHeight
        root.minWidth = minWidth

        with(root) {
            form {
                fieldset {
                    children.bind(model.questions) {
                        field(it.item.label) {
                            add(ViewFactory().createQuestionField(it))
                        }
                    }
                }
                button("Save") {
                    action {
                        save()
                    }
                }
            }

            runAsync {
                model.load()
            }
        }
    }

    private fun save() {
        model.commit()
        model.load()
    }
}
