package doge.ast

import doge.ast.node.expression.SourceLocation
import doge.common.Name
import doge.data.question.Question
import doge.data.symbol.SymbolTable
import doge.node.ExpressionNode
import doge.node.Node
import doge.node.QuestionNode
import doge.node.RootNode
import java.util.*

class FormTreeBuilder(var symbolTable: SymbolTable) {

    private val nodeStack = ArrayDeque<Node>()

    init {
        nodeStack.push(RootNode(symbolTable))
    }

    fun pushQuestion(question: Question) {
        nodeStack.first.addChild(QuestionNode(symbolTable, question))
    }

    fun pushExpression(reference: Name, sourceLocation: SourceLocation) {
        nodeStack.push(ExpressionNode(symbolTable, reference, sourceLocation))
    }

    fun build(): Node {
        val node = nodeStack.pop()

        if (!nodeStack.isEmpty()) {
            nodeStack.first.addChild(node)
        }

        return node
    }
}