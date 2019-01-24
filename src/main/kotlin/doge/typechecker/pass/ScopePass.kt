package doge.typechecker.pass

import doge.ast.node.expression.SourceLocation
import doge.common.Name
import doge.data.symbol.SymbolTable
import doge.node.ExpressionNode
import doge.node.Node
import doge.node.QuestionNode
import doge.node.RootNode
import doge.typechecker.NodePass
import doge.typechecker.TokenLocation
import doge.typechecker.TypeCheckResult
import java.util.*

class ScopePass(result: TypeCheckResult, val symbolTable: SymbolTable) : NodePass<Unit>(result) {

    private val visibleReferences = ArrayDeque<Name>()

    override fun visit(node: Node) {
        val oldSize = registerReferencesForChildren(node)

        node.children.forEach { child -> child.accept(this) }

        popReferencesToLevel(oldSize)
    }

    override fun visit(rootNode: RootNode) {
        val oldSize = registerReferencesForChildren(rootNode)

        rootNode.children.forEach { child -> child.accept(this) }

        popReferencesToLevel(oldSize)
    }

    override fun visit(questionNode: QuestionNode) {
        val oldSize = registerReferencesForChildren(questionNode)

        findAllUndefinedReferences(questionNode.question.name, questionNode.question.nameLocation)

        questionNode.children.forEach { child -> child.accept(this) }

        popReferencesToLevel(oldSize)
    }

    override fun visit(expressionNode: ExpressionNode) {
        findAllUndefinedReferences(expressionNode.reference, expressionNode.sourceLocation)

        val oldSize = registerReferencesForChildren(expressionNode)

        expressionNode.children.forEach { child -> child.accept(this) }

        popReferencesToLevel(oldSize)
    }

    private fun findAllUndefinedReferences(reference: Name, sourceLocation: SourceLocation) {
        val symbol = symbolTable.findSymbol(reference)

        if (symbol == null || reference !in visibleReferences) {
            val error = TokenLocation(reference, sourceLocation)
            result.undefinedReferences.add(error)
        } else if (symbol.expression != null) {
            val references = symbol.expression.allReferences()

            references.forEach { r -> findAllUndefinedReferences(r.name, r.sourceLocation) }
        }
    }

    private fun registerReferencesForChildren(node: Node): Int {
        val oldSize = visibleReferences.size

        collectReferences(node).forEach { visibleReferences.push(it) }

        return oldSize
    }

    private fun collectReferences(node: Node) = node.children.mapNotNull {
        when (it) {
            is QuestionNode -> it.question.name
            is ExpressionNode -> it.reference
            else -> null
        }
    }

    private fun popReferencesToLevel(level: Int) {
        while (visibleReferences.size > level) {
            visibleReferences.pop()
        }
    }

}