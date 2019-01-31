package ql.ast.node.expression

import ql.ast.location.SourceLocation
import ql.ast.node.QLNode

abstract class Expression(location: SourceLocation) : QLNode(location)
