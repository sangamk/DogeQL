package doge.typechecker

import doge.ast.node.expression.SourceLocation
import doge.common.Name
import doge.data.question.SymbolType

data class TokenLocation(val name: Name, val sourceLocation: SourceLocation)
data class TypeLocation(val name: Name, val type: SymbolType, val sourceLocation: SourceLocation)

data class DuplicateError(val original: TokenLocation, val redefinition: TokenLocation)
data class TypeRedefinitionError(val original: TypeLocation, val redefinitionType: SymbolType)

data class CircularDependencyError(val source: TokenLocation, val clash: Name)

class TypeCheckResult {

    val undefinedReferences = mutableListOf<TokenLocation>()
    val duplicateLabels = mutableListOf<DuplicateError>()
    val duplicateNames = mutableListOf<DuplicateError>()
    val typeConflicts = mutableListOf<TypeRedefinitionError>()
    val typeErrors = mutableListOf<TypeRedefinitionError>()
    val circularErrors = mutableListOf<CircularDependencyError>()

    fun hasErrors(): Boolean {
        return undefinedReferences.isNotEmpty()
                || duplicateLabels.isNotEmpty()
                || duplicateNames.isNotEmpty()
                || typeConflicts.isNotEmpty()
                || typeErrors.isNotEmpty()
                || circularErrors.isNotEmpty()
    }

    fun printErrors() {
        undefinedReferences.forEach { t -> printUndefinedReference(t) }
        duplicateLabels.forEach { t -> printDuplicateLabelError(t) }
        duplicateNames.forEach { t -> printDuplicateNameError(t) }
        circularErrors.forEach { t -> printCircularErrors(t) }
        typeConflicts.forEach { t -> printTypeConflictError(t) }
        typeErrors.forEach { t -> printTypeError(t) }
    }

    private fun printUndefinedReference(error: TokenLocation) {
        println("Error: undefined reference \"${error.name}\" " +
                "on line ${error.sourceLocation.line}:${error.sourceLocation.column}.")
    }

    private fun printDuplicateLabelError(error: DuplicateError) {
        println("Error: duplicate label ${error.redefinition.name} " +
                "on line ${error.redefinition.sourceLocation.line}:${error.redefinition.sourceLocation.column} " +
                "(first declared here: ${error.original.sourceLocation.line}:${error.original.sourceLocation.column}).")
    }

    private fun printDuplicateNameError(error: DuplicateError) {
        println("Error: duplicate name \"${error.redefinition.name}\" " +
                "on line ${error.redefinition.sourceLocation.line}:${error.redefinition.sourceLocation.column} " +
                "(first declared here: ${error.original.sourceLocation.line}:${error.original.sourceLocation.column}).")
    }

    private fun printCircularErrors(error: CircularDependencyError) {
        println("Error: \"${error.source.name}\" " +
                "on line ${error.source.sourceLocation.line}:${error.source.sourceLocation.column} " +
                "depends on \"${error.clash}\" which is a circular dependency.")
    }

    private fun printTypeConflictError(error: TypeRedefinitionError) {
        println("Error: unable to assign type ${error.redefinitionType.name} " +
                "to \"${error.original.name}\" of type ${error.original.type.name} " +
                "on line ${error.original.sourceLocation.line}:${error.original.sourceLocation.column}.")
    }

    private fun printTypeError(error: TypeRedefinitionError) {
        println("Error: result of doge.expression at " +
                "${error.original.sourceLocation.line}:${error.original.sourceLocation.column} " +
                "is undefined.")
    }
}