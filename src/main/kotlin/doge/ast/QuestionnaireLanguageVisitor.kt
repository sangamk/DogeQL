package doge.ast

import QuestionnaireLanguageGrammarBaseVisitor
import QuestionnaireLanguageGrammarParser
import doge.ast.node.*
import doge.ast.node.expression.*
import doge.ast.node.expression.operation.BinaryOperation
import doge.ast.node.expression.operation.UnaryOperation
import doge.common.location
import doge.data.question.SymbolType
import doge.data.value.*


class QuestionnaireLanguageVisitor : QuestionnaireLanguageGrammarBaseVisitor<QLNode>() {
    override fun visitForm(ctx: QuestionnaireLanguageGrammarParser.FormContext?): QLNode {
        val context = ctx!!

        val identifier = Identifier(context.NAME().text, context.NAME().location())

        val block = visit(context.block()) as Block

        return Form(identifier, block, context.FORM().location())
    }

    override fun visitBlock(ctx: QuestionnaireLanguageGrammarParser.BlockContext?): QLNode {
        val context = ctx!!

        val statements = context.statement().map { visit(it) as Statement }

        return Block(statements)
    }

    override fun visitQuestionStatement(ctx: QuestionnaireLanguageGrammarParser.QuestionStatementContext?): QLNode {
        val context = ctx!!

        val label = context.LIT_STRING().text
        val name = context.NAME().text
        val type = SymbolType.valueOf(context.TYPE().text.toUpperCase())

        val expression = context.expression()?.let { visit(it) } as Expression?

        return QuestionStatement(label, name, type, expression)
    }

    override fun visitIfStatement(ctx: QuestionnaireLanguageGrammarParser.IfStatementContext?): QLNode {
        val context = ctx!!

        val expression = visit(context.expression()) as Expression
        val block = visit(context.block()) as Block

        return IfStatement(expression, block)
    }

    override fun visitBinaryExpression(ctx: QuestionnaireLanguageGrammarParser.BinaryExpressionContext?): QLNode {
        val context = ctx!!

        val left = visit(context.left) as Expression
        val right = visit(context.right) as Expression
        val operator = BinaryOperation.fromString(context.operator.text)

        return BinaryExpression(left, right, operator, context.operator.location())
    }

    override fun visitParenthesisExpresion(ctx: QuestionnaireLanguageGrammarParser.ParenthesisExpresionContext?): QLNode {
        val context = ctx!!

        return visit(context.expression()) as Expression
    }

    override fun visitReferenceExpression(ctx: QuestionnaireLanguageGrammarParser.ReferenceExpressionContext?): QLNode {
        val context = ctx!!

        return ReferenceExpression(context.reference.text, context.reference.location())
    }

    override fun visitUnaryExpression(ctx: QuestionnaireLanguageGrammarParser.UnaryExpressionContext?): QLNode {
        val context = ctx!!

        val expression = visit(context.expression()) as Expression
        val operator = UnaryOperation.fromString(context.operator.text)

        return UnaryExpression(expression, operator, context.operator.location())
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