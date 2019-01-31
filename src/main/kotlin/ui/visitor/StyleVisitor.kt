package ui.visitor

import javafx.geometry.Side
import javafx.scene.Node
import javafx.scene.control.Label
import qls.ast.node.*
import qls.ast.node.attribute.Attribute
import qls.ast.node.attribute.DefaultAttributes
import qls.visitor.QlsVisitor
import tornadofx.*
import ui.controller.DogeController
import ui.model.viewmodel.QuestionViewModel
import ui.model.viewmodel.ViewModelFactory
import ui.view.field.QuestionFieldFactory

class StyleVisitor : View(), QlsVisitor<Node> {

    override fun visit(attributes: Attribute): Node {
        throw IllegalStateException("Unable to visit attribute")
    }

    private val controller: DogeController by inject()

    private var padding = 0.0

    var flatLayout = hashMapOf<String, Node>()
    var questionViewModels = hashMapOf<String, QuestionViewModel>()

    override val root = Drawer(Side.LEFT, false, false)

    override fun visit(styleSheet: StyleSheet): Node {
        styleSheet.pages.forEach {
            it.accept(this)
        }
        flatLayout[styleSheet.name] = root
        return root
    }

    override fun visit(page: Page): Node {

        with(root) {
            val item = item(page.name) {
                scrollpane {
                    form {
                        page.styles.forEach {
                            add(it.accept(this@StyleVisitor))
                        }
                    }
                }
            }
            flatLayout[page.name] = item
        }

        return root
    }

    override fun visit(style: Style): Node {
        return style.accept(this)
    }

    override fun visit(section: Section): Node {
        val fieldSet = Fieldset(section.title)

        fieldSet.paddingLeft = padding

        padding += 10

        section.elements.forEach {
            fieldSet.add(it.accept(this))
        }

        padding -= 10

        flatLayout[section.title] = fieldSet

        return fieldSet
    }

    override fun visit(question: Question): Node {

        val field = Field()

        if (controller.hasQuestion(question.name)) {

            val dataQuestion = controller.getQuestion(question.name)

            val viewModel = ViewModelFactory().createQuestionViewModel(dataQuestion)
            val questionField = QuestionFieldFactory().createQuestionField(viewModel)

            field.isVisible = dataQuestion.visible
            field.text = dataQuestion.label

            field.add(questionField)

            questionViewModels[question.name] = viewModel
        }

        flatLayout[question.name] = field

        return field
    }

    override fun visit(element: Element): Node {
        return element.accept(this)
    }

    override fun visit(defaultAttributes: DefaultAttributes): Node {
        val label = Label()// Placeholder
        label.isManaged = false
        label.isVisible = false
        return label
    }
}