package lexer

import QuestionareLanguageParser
import QuestionareLanguageLexer
import org.antlr.v4.runtime.*

class ParserHost(input: String, expectError: Boolean = false) {

    val lexer: QuestionareLanguageLexer
    val tokens: CommonTokenStream
    val parser: QuestionareLanguageParser

    init {
        val stream = ANTLRInputStream(input)
        lexer = QuestionareLanguageLexer(stream)
        tokens = CommonTokenStream(lexer)
        parser = QuestionareLanguageParser(tokens)

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