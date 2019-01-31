package ql.typechecker.duplication

import ql.ast.location.SourceLocation
import ql.typechecker.ErrorContext

data class DuplicationError(val text: String, val original: SourceLocation, val redefinition: SourceLocation)

class DuplicationErrorContext : ErrorContext {

    val labelDuplications = mutableListOf<DuplicationError>()
    val nameDuplications = mutableListOf<DuplicationError>()

    override fun collect(): List<String> {
        return labelDuplications.map {
            "duplicate question label ${it.text} at ${it.redefinition} (first defined at ${it.original})"
        } + nameDuplications.map {
            "duplicate question identifier \"${it.text}\" at ${it.redefinition} (first defined at ${it.original})"
        }
    }

    override fun hasErrors() = labelDuplications.isNotEmpty() || nameDuplications.isNotEmpty()

}