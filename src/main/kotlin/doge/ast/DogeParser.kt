package doge.ast

import QuestionnaireLanguageGrammarLexer
import QuestionnaireLanguageGrammarParser
import doge.ast.node.QLNode
import doge.data.question.Question
import doge.node.Node
import doge.visitor.CircularDependencyVisitor
import doge.visitor.DuplicateQuestionVisitor
import doge.visitor.UiVisitor
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream


class DogeParser {

    fun parse(): List<Question> {
        val fileStream = javaClass.getResource("/sample/TestQuestionare.doge").openStream()

        val stream = ANTLRInputStream(fileStream)
        val lexer = QuestionnaireLanguageGrammarLexer(stream)
        val tokens = CommonTokenStream(lexer)
        val parser = QuestionnaireLanguageGrammarParser(tokens)

        val visitor = QuestionnaireLanguageVisitor()

        val ast = visitor.visit(parser.form())
        val questions = UiVisitor().visit(ast)
//        DuplicateQuestionVisitor().visit(ast)
//
//        CircularDependencyVisitor().visit(ast)

//        val listener = DogeListener()

//        walker.walk(listener, parser.form())

//        val tree = listener.getParsedDogeLanguage()

//        val result = TypeChecker(listener.symbolTable).check(tree)

//        if (result.hasErrors()) {
//            result.printErrors()
//
//            throw Exception() // TODO: fix this flow
//        }

        return questions
    }

}