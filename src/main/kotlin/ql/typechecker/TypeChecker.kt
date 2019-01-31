package ql.typechecker

import ql.ast.node.QLNode
import ql.data.symbol.SymbolTable
import ql.typechecker.circular.CircularDependencyErrorContext
import ql.typechecker.circular.CircularDependencyVisitor
import ql.typechecker.circular.QuestionLookupVisitor
import ql.typechecker.duplication.DuplicateQuestionVisitor
import ql.typechecker.duplication.DuplicationErrorContext
import ql.typechecker.scope.ScopeErrorContext
import ql.typechecker.scope.ScopeVisitor
import ql.typechecker.type.TypeErrorContext
import ql.typechecker.type.TypeVisitor

class TypeChecker(private val symbolTable: SymbolTable, private val root: QLNode) {

    fun check(): MutableList<String> {
        val allErrors = mutableListOf<String>()

        allErrors += checkDuplicates().collect()
        allErrors += checkCircularDependencies().collect()
        allErrors += checkUndefinedReferences().collect()

        if (allErrors.isNotEmpty()) {
            printErrors(allErrors)
            return allErrors
        }

        allErrors += checkTypes().collect()

        if (allErrors.isNotEmpty()) {
            printErrors(allErrors)
        }

        return allErrors
    }

    private fun checkDuplicates(): DuplicationErrorContext {
        return DuplicationErrorContext().apply {
            DuplicateQuestionVisitor(this).visit(root)
        }
    }

    private fun checkCircularDependencies(): CircularDependencyErrorContext {
        return CircularDependencyErrorContext().apply {
            CircularDependencyVisitor(this, { reference ->
                QuestionLookupVisitor(reference).visit(root)
            }).visit(root)
        }
    }

    private fun checkUndefinedReferences(): ScopeErrorContext {
        return ScopeErrorContext().apply {
            ScopeVisitor(this).visit(root)
        }
    }

    private fun checkTypes(): TypeErrorContext {
        return TypeErrorContext().apply {
            TypeVisitor(
                    this,
                    { reference ->
                        symbolTable.findSymbol(reference)?.let {
                            it
                        } ?: run {
                            QuestionLookupVisitor(reference).visit(root)?.let {
                                symbolTable.registerSymbol(it.name.text, it.type.type.getDefaultInstance())
                                symbolTable.findSymbol(reference)
                            }
                        }
                    },
                    { name, value ->
                        symbolTable.registerSymbol(name, value)
                    }
            ).visit(root)
        }
    }

    private fun collectFormattedErrors(errors: List<String>): List<String> {
        val indexTextLength = errors.size.toString().length

        val errorFormat = "Error [%${indexTextLength}d/%${indexTextLength}d]: %s"

        return errors.mapIndexed { index, error ->
            errorFormat.format(index + 1, errors.size, error)
        }
    }

    private fun printErrors(errors: List<String>) {
        collectFormattedErrors(errors).forEach { println(it) }
    }

}