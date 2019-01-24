package doge.typechecker

import doge.node.ExpressionNode
import doge.node.Node
import doge.node.QuestionNode
import doge.node.RootNode

abstract class NodePass<out O>(val result: TypeCheckResult) {

    abstract fun visit(node: Node): O

    abstract fun visit(rootNode: RootNode): O

    abstract fun visit(questionNode: QuestionNode): O

    abstract fun visit(expressionNode: ExpressionNode): O

}