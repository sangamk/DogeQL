package qls.ast

import QuestionnaireLanguageStyleGrammarBaseVisitor
import QuestionnaireLanguageStyleGrammarParser
import qls.ast.node.*
import qls.ast.node.attribute.Attribute
import qls.ast.node.attribute.AttributePair
import qls.ast.node.attribute.DefaultAttributes
import qls.ast.node.widget.Widget
import qls.ast.node.widget.WidgetType

class QuestionnaireLanguageStyleVisitor : QuestionnaireLanguageStyleGrammarBaseVisitor<QlsNode>() {

    override fun visitStylesheet(ctx: QuestionnaireLanguageStyleGrammarParser.StylesheetContext?): QlsNode {
        val context = ctx!!

        val name = context.NAME().text

        val pages = context.page().map {
            it.accept(this) as Page
        }

        return StyleSheet(pages, name)
    }

    override fun visitPage(ctx: QuestionnaireLanguageStyleGrammarParser.PageContext?): QlsNode {
        val context = ctx!!

        val name = context.NAME().text

        val styles = context.style().map {
            it.accept(this) as Style
        }

        return Page(styles, name)
    }

    override fun visitStyle(ctx: QuestionnaireLanguageStyleGrammarParser.StyleContext?): QlsNode {
        val context = ctx!!


        context.defaultAttributes()?.let {
            return it.accept(this) as DefaultAttributes
        }

        context.section()?.let {
            return it.accept(this) as Section
        }

        throw IllegalStateException("Unreachable state")
    }

    override fun visitSection(ctx: QuestionnaireLanguageStyleGrammarParser.SectionContext?): QlsNode {
        val context = ctx!!

        val name = context.LIT_STRING().text


        val elements = context.element().element().map {
            it.accept(this) as Element
        }

        return Section(name, elements)
    }

    override fun visitElement(ctx: QuestionnaireLanguageStyleGrammarParser.ElementContext?): QlsNode {
        val context = ctx!!

        context.defaultAttributes()?.let {
            return it.accept(this)
        }

        context.question()?.let {
            return it.accept(this)
        }

        context.section()?.let {
            return it.accept(this)
        }

        throw IllegalStateException("Unreachable state")
    }

    override fun visitQuestion(ctx: QuestionnaireLanguageStyleGrammarParser.QuestionContext?): QlsNode {
        val context = ctx!!

        val name = context.NAME().text

        return Question(name)
    }

    override fun visitDefaultAttributes(ctx: QuestionnaireLanguageStyleGrammarParser.DefaultAttributesContext?): QlsNode {
        val context = ctx!!

        val type = context.TYPE().text

        val attributes = context.attributes().attribute().map {
            it.accept(this) as Attribute
        }

        return DefaultAttributes(type, attributes)
    }

    override fun visitPair(ctx: QuestionnaireLanguageStyleGrammarParser.PairContext?): QlsNode {
        val context = ctx!!

        val name = context.NAME().text

        return AttributePair(name, context.literal().text)
    }

    override fun visitWidget(ctx: QuestionnaireLanguageStyleGrammarParser.WidgetContext?): QlsNode {
        val context = ctx!!

        val name = context.NAME().text

        val type = WidgetType.valueOf(name.toUpperCase())

        return Widget(type)
    }
}