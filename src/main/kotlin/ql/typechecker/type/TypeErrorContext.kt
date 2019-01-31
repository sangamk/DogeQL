package ql.typechecker.type

import ql.ast.location.Type
import ql.ast.node.expression.operation.BinaryOperation
import ql.ast.node.expression.operation.UnaryOperation
import ql.typechecker.ErrorContext

data class TypeDeclarationError(val left: Type, val right: Type)
data class UnaryOperationError(val type: Type, val operation: UnaryOperation)
data class BinaryOperationError(val left: Type, val right: Type, val operation: BinaryOperation)

class TypeErrorContext : ErrorContext {

    val declarationErrors = mutableListOf<TypeDeclarationError>()
    val unaryOperationErrors = mutableListOf<UnaryOperationError>()
    val binaryOperationErrors = mutableListOf<BinaryOperationError>()

    override fun collect(): List<String> {
        return declarationErrors.map {
            "Unable to assign value of type " +
                    "${it.right.type} at ${it.right.location} to " +
                    "${it.left.type} at ${it.left.location}."
        } + unaryOperationErrors.map {
            "Unable to apply operator ${it.operation} to value of type ${it.type.type} at ${it.type.location}"
        } + binaryOperationErrors.map {
            "Unable to apply operator ${it.operation} " +
                    "to ${it.left.type} at ${it.left.location} " +
                    "and ${it.right.type} at ${it.right.location}."
        }
    }

    override fun hasErrors() = declarationErrors.isNotEmpty()

}