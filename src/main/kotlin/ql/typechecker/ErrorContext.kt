package ql.typechecker

interface ErrorContext {

    fun collect(): List<String>

    fun hasErrors(): Boolean

}