package ql.ast

import QuestionnaireLanguageGrammarLexer
import QuestionnaireLanguageGrammarParser
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import ql.ast.node.QLNode
import ql.data.symbol.SymbolTable
import ql.typechecker.TypeChecker
import ql.visitor.ValueUpdateVisitor
import java.io.File
import java.io.FileInputStream


data class DogeParseResult(val ast: QLNode?, val symbolTable: SymbolTable?, val info: List<String>)

class DogeParser {

    fun parse(file: File): DogeParseResult? {
        val fileName = file.path

        var errors: MutableList<String>

        FileInputStream(fileName).use {
            val stream = ANTLRInputStream(it)
            val lexer = QuestionnaireLanguageGrammarLexer(stream)
            val tokens = CommonTokenStream(lexer)
            val parser = QuestionnaireLanguageGrammarParser(tokens)
            val visitor = QuestionnaireLanguageVisitor()
            val ast = visitor.visit(parser.form())

            val symbolTable = SymbolTable()
            errors = TypeChecker(symbolTable, ast).check()

            if (errors.isEmpty()) {
                ValueUpdateVisitor.default(symbolTable).visit(ast)

            }

            return DogeParseResult(ast, symbolTable, errors)

        }
    }

}