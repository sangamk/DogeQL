package doge.ast.node.expression

import doge.ast.node.QLNode
import doge.ast.node.expression.visitor.evaluation.EvaluationVisitor
import doge.ast.node.expression.visitor.evaluation.TypeVisitor
import doge.ast.node.expression.visitor.reference.ReferenceCollector
import doge.ast.node.expression.visitor.reference.ReferenceVisitor
import doge.data.question.SymbolType
import doge.data.symbol.SymbolTable
import doge.data.value.BaseSymbolValue

abstract class Expression(val sourceLocation: SourceLocation) : QLNode {

    abstract fun accept(visitor: EvaluationVisitor): BaseSymbolValue

    abstract fun accept(visitor: TypeVisitor): SymbolType

    abstract fun accept(visitor: ReferenceVisitor): Boolean

    abstract fun accept(visitor: ReferenceCollector)

    fun containsReference(): Boolean {
        return !accept(ReferenceVisitor())
    }

    fun evaluate(symbolTable: SymbolTable): BaseSymbolValue {
        return accept(EvaluationVisitor(symbolTable))
    }

    fun determineType(symbolTable: SymbolTable): SymbolType {
        return accept(TypeVisitor(symbolTable))
    }

    fun allReferences(): List<ReferenceExpression> {
        val collector = ReferenceCollector()
        accept(collector)

        return collector.references
    }
}
