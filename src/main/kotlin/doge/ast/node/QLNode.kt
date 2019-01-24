package doge.ast.node

import doge.visitor.QuestionnaireASTBaseVisitor

interface QLNode {

    fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T

}