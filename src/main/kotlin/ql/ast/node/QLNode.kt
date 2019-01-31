package ql.ast.node

import ql.ast.location.SourceLocation
import ql.visitor.QuestionnaireASTBaseVisitor

abstract class QLNode(val location: SourceLocation) {

    abstract fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T

}