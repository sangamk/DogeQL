package ql.ast

import QuestionnaireLanguageGrammarBaseVisitor
import QuestionnaireLanguageGrammarParser
import ql.ast.location.Identifier
import ql.ast.location.Type
import ql.ast.node.Block
import ql.ast.node.Form
import ql.ast.node.QLNode
import ql.ast.node.expression.*
import ql.ast.node.expression.operation.BinaryOperation
import ql.ast.node.expression.operation.UnaryOperation
import ql.ast.node.statement.IfStatement
import ql.ast.node.statement.QuestionStatement
import ql.ast.node.statement.Statement
import ql.common.location
import ql.data.symbol.SymbolType
import ql.data.value.*


class QuestionnaireLanguageVisitor : QuestionnaireLanguageGrammarBaseVisitor<QLNode>() {
    override fun visitForm(ctx: QuestionnaireLanguageGrammarParser.FormContext?): QLNode {
        val context = ctx!!

        val identifier = Identifier(context.NAME().text, context.NAME().location())
        val block = visit(context.block()) as Block

        return Form(identifier, block, context.location())
    }

    override fun visitBlock(ctx: QuestionnaireLanguageGrammarParser.BlockContext?): QLNode {
        val context = ctx!!

        val statements = context.statement().map { visit(it) as Statement }

        return Block(statements, context.location())
    }

    override fun visitQuestionStatement(ctx: QuestionnaireLanguageGrammarParser.QuestionStatementContext?): QLNode {
        val context = ctx!!

        val label = Identifier(context.LIT_STRING().text, context.LIT_STRING().location())
        val name = Identifier(context.NAME().text, context.NAME().location())
        val type = Type(SymbolType.valueOf(context.TYPE().text.toUpperCase()), context.TYPE().location())

        val expression = context.expression()?.let { visit(it) } as Expression?

        return QuestionStatement(label, name, type, expression, context.location())
    }

    override fun visitIfStatement(ctx: QuestionnaireLanguageGrammarParser.IfStatementContext?): QLNode {
        val context = ctx!!

        val expression = visit(context.expression()) as Expression
        val block = visit(context.block()) as Block

        return IfStatement(expression, block, context.location())
    }

    override fun visitBinaryExpression(ctx: QuestionnaireLanguageGrammarParser.BinaryExpressionContext?): QLNode {
        val context = ctx!!

        val left = visit(context.left) as Expression
        val right = visit(context.right) as Expression
        val operator = BinaryOperation.fromString(context.operator.text)

        return BinaryExpression(left, right, operator, context.location())
    }

    override fun visitParenthesisExpresion(ctx: QuestionnaireLanguageGrammarParser.ParenthesisExpresionContext?): QLNode {
        val context = ctx!!

        return visit(context.expression()) as Expression
    }

    override fun visitReferenceExpression(ctx: QuestionnaireLanguageGrammarParser.ReferenceExpressionContext?): QLNode {
        val context = ctx!!

        val identifier = Identifier(context.reference.text, context.reference.location())

        return ReferenceExpression(identifier, context.location())
    }

    override fun visitUnaryExpression(ctx: QuestionnaireLanguageGrammarParser.UnaryExpressionContext?): QLNode {
        val context = ctx!!

        val expression = visit(context.expression()) as Expression
        val operator = UnaryOperation.fromString(context.operator.text)

        return UnaryExpression(expression, operator, context.location())
    }

    override fun visitLiteral(ctx: QuestionnaireLanguageGrammarParser.LiteralContext?): QLNode {
        val context = ctx!!

        context.LIT_BOOLEAN()?.let {
            return LiteralExpression(BooleanValue(it.text), it.location())
        }

        context.LIT_INTEGER()?.let {
            return LiteralExpression(IntegerValue(it.text), it.location())
        }

        context.LIT_DECIMAL()?.let {
            return LiteralExpression(DecimalValue(it.text), it.location())
        }

        context.LIT_STRING()?.let {
            return LiteralExpression(StringValue(it.text), it.location())
        }

        context.LIT_COLOR()?.let {
            return LiteralExpression(ColorValue(it.text), it.location())
        }

        throw IllegalStateException("Undefined literal type ${context.text}")
    }
}