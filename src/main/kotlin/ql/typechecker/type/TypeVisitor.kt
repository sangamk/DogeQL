package ql.typechecker.type

import ql.ast.location.Type
import ql.ast.node.Block
import ql.ast.node.Form
import ql.ast.node.expression.BinaryExpression
import ql.ast.node.expression.LiteralExpression
import ql.ast.node.expression.ReferenceExpression
import ql.ast.node.expression.UnaryExpression
import ql.ast.node.statement.IfStatement
import ql.ast.node.statement.QuestionStatement
import ql.data.symbol.SymbolRegistrationResult
import ql.data.symbol.SymbolType
import ql.data.value.BaseSymbolValue
import ql.visitor.QuestionnaireASTBaseVisitor

class TypeVisitor(
        private val context: TypeErrorContext,
        private val requestValue: (reference: String) -> BaseSymbolValue?,
        private val registerValue: (name: String, value: BaseSymbolValue) -> SymbolRegistrationResult
) : QuestionnaireASTBaseVisitor<Type> {

    override fun visit(form: Form): Type {
        return visit(form.block)
    }

    override fun visit(block: Block): Type {
        return block.statements.map { visit(it) }.firstOrNull()?.let {
            it
        } ?: run {
            Type(SymbolType.UNDEFINED, block.location)
        }
    }

    override fun visit(ifStatement: IfStatement): Type {
        visit(ifStatement.block)
        return visit(ifStatement.expression)
    }

    override fun visit(questionStatement: QuestionStatement): Type {
        registerValue(questionStatement.name.text, questionStatement.type.type.getDefaultInstance())

        questionStatement.expression?.let {
            val declaredType = questionStatement.type
            val inferredType = visit(it)

            if (inferredType.type != SymbolType.UNDEFINED) {
                val castedType = inferredType.type.getDefaultInstance().castTo(declaredType.type)

                if (castedType == null) {
                    context.declarationErrors += TypeDeclarationError(declaredType, inferredType)
                }
            }

            return inferredType
        }

        return questionStatement.type
    }

    override fun visit(binaryExpression: BinaryExpression): Type {
        val leftType = visit(binaryExpression.left)
        val rightType = visit(binaryExpression.right)
        val resultType = binaryExpression.operation.getResolvedType(leftType.type, rightType.type)

        if (leftType.type != SymbolType.UNDEFINED && rightType.type != SymbolType.UNDEFINED) {
            if (resultType == SymbolType.UNDEFINED) {
                context.binaryOperationErrors += BinaryOperationError(leftType, rightType, binaryExpression.operation)
            }
        }

        return Type(resultType, binaryExpression.location)
    }

    override fun visit(unaryExpression: UnaryExpression): Type {
        val nextType = visit(unaryExpression.next)
        val resultType = unaryExpression.operation.getResolveType(nextType.type)

        if (nextType.type != SymbolType.UNDEFINED) {
            if (resultType == SymbolType.UNDEFINED) {
                context.unaryOperationErrors += UnaryOperationError(nextType, unaryExpression.operation)
            }
        }

        return Type(resultType, unaryExpression.location)
    }

    override fun visit(referenceExpression: ReferenceExpression): Type {
        val value = requestValue(referenceExpression.name.text)
                ?: throw IllegalStateException("Unable to find reference ${referenceExpression.name}")

        return Type(value.type, referenceExpression.location)
    }

    override fun visit(literalExpression: LiteralExpression): Type {
        return Type(literalExpression.value.type, literalExpression.location)
    }

}