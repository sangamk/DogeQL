package lexer

import QuestionnaireLanguageGrammarLexer
import QuestionnaireLanguageGrammarParser
import org.antlr.v4.runtime.*

class ParserHost(input: String, expectError: Boolean = false) {

    val lexer: QuestionnaireLanguageGrammarLexer
    val tokens: CommonTokenStream
    val parser: QuestionnaireLanguageGrammarParser

    init {
        val stream = ANTLRInputStream(input)
        lexer = QuestionnaireLanguageGrammarLexer(stream)
        tokens = CommonTokenStream(lexer)
        parser = QuestionnaireLanguageGrammarParser(tokens)

        parser.errorHandler = ErrorHandler(expectError)
    }

    class ErrorHandler(private val expectError: Boolean) : DefaultErrorStrategy() {

        override fun reportError(recognizer: Parser?, e: RecognitionException?) {

            if (!expectError) {
                super.reportError(recognizer, e)
            }

        }

    }
}