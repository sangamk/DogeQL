package doge.typechecker.pass

import doge.data.question.Question
import doge.node.ExpressionNode
import doge.node.Node
import doge.node.QuestionNode
import doge.node.RootNode
import doge.typechecker.DuplicateError
import doge.typechecker.NodePass
import doge.typechecker.TokenLocation
import doge.typechecker.TypeCheckResult

class DuplicatePass(result: TypeCheckResult) : NodePass<Unit>(result) {

    private val visitedQuestions = hashSetOf<Question>()

    override fun visit(node: Node) {
        node.children.forEach { child -> child.accept(this) }
    }

    override fun visit(rootNode: RootNode) {
        rootNode.children.forEach { child -> child.accept(this) }
    }

    override fun visit(questionNode: QuestionNode) {
        val label = questionNode.question.label
        val name = questionNode.question.name

        val originalByLabel = visitedQuestions.find { question -> question.label == label }
        if (originalByLabel != null) {
            val original = TokenLocation(originalByLabel.label, originalByLabel.labelLocation)
            val duplicate = TokenLocation(label, questionNode.question.labelLocation)
            val error = DuplicateError(original, duplicate)

            result.duplicateLabels.add(error)
        }

        val originalByName = visitedQuestions.find { question -> question.name == name }
        if (originalByName != null) {
            val original = TokenLocation(originalByName.name, originalByName.nameLocation)
            val duplicate = TokenLocation(name, questionNode.question.nameLocation)
            val error = DuplicateError(original, duplicate)

            result.duplicateNames.add(error)
        }

        visitedQuestions.add(questionNode.question)

        questionNode.children.forEach { child -> child.accept(this) }
    }

    override fun visit(expressionNode: ExpressionNode) {
        expressionNode.children.forEach { child -> child.accept(this) }
    }

}