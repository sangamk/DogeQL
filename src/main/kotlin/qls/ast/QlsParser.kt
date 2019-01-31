package qls.ast

import QuestionnaireLanguageStyleGrammarLexer
import QuestionnaireLanguageStyleGrammarParser
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import qls.ast.node.QlsNode
import java.io.File
import java.io.FileInputStream


class QlsParser {
    fun parse(file: File): QlsNode {
        FileInputStream(file).use {
            val stream = ANTLRInputStream(it)
            val lexer = QuestionnaireLanguageStyleGrammarLexer(stream)
            val tokens = CommonTokenStream(lexer)
            val parser = QuestionnaireLanguageStyleGrammarParser(tokens)

            val visitor = QuestionnaireLanguageStyleVisitor()

            return visitor.visit(parser.stylesheet())
        }
    }

}