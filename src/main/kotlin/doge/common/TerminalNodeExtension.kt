package doge.common

import doge.ast.node.expression.SourceLocation
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.TerminalNode

fun TerminalNode.location() = SourceLocation(
        symbol.line, symbol.charPositionInLine, symbol.startIndex, symbol.stopIndex
)

fun Token.location()= SourceLocation(
        line, charPositionInLine, startIndex, stopIndex
)