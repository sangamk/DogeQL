package ql.ast.location

class SourceLocation(private val line: Int, private val column: Int) {

    override fun toString() = "$line:${column + 1}"

}