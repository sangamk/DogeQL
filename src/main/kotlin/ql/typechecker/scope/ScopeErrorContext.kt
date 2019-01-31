package ql.typechecker.scope

import ql.ast.location.Identifier
import ql.typechecker.ErrorContext

class ScopeErrorContext : ErrorContext {

    val errors = mutableListOf<Identifier>()

    override fun collect(): List<String> {
        return errors.map {
            "Undefined reference to \"${it.text}\" at ${it.location}"
        }
    }

    override fun hasErrors() = errors.isNotEmpty()

}