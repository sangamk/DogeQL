package ui.view.fragment

import tornadofx.Drawer
import tornadofx.Fragment
import tornadofx.drawer
import tornadofx.onChange
import ui.controller.DogeController
import ui.visitor.StyleVisitor

class StylizedFormFragment : Fragment() {

    private val controller: DogeController by inject()
    private val visitor = StyleVisitor()
    override var root = drawer { }

    init {
        val style = controller.style
        style?.let {
            val result = it.accept(visitor)
            root = result as Drawer
        }

        controller.questions.onChange {
            controller.questions.forEach { question ->
                visitor.flatLayout[question.name]?.let {
                    it.isVisible = question.visible
                    it.isManaged = question.visible

                }
                visitor.questionViewModels[question.name]?.setViewModelValue(question)
            }
        }
    }

}
