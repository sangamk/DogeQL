package ql.typechecker.circular

import ql.ast.location.Identifier
import ql.typechecker.ErrorContext

class CircularDependencyError(val head: Identifier, val dependencies: Collection<Identifier>)

class CircularDependencyErrorContext : ErrorContext {

    val errors = mutableListOf<CircularDependencyError>()

    override fun collect(): List<String> {
        return errors.map {
            "reference \"${it.head.text}\" at ${it.head.location}\n" +
                    it.dependencies.joinToString(",\n\twhich depends on ", "\tdepends on ") {
                        "reference \"${it.text}\" at ${it.location}"
                    } +
                    ",\n\tforming an unsolvable circular dependency."
        }
    }

    override fun hasErrors() = errors.isNotEmpty()

}