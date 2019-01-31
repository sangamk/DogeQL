package ql.ast.node.statement

import ql.ast.location.SourceLocation
import ql.ast.node.QLNode

abstract class Statement(location: SourceLocation) : QLNode(location)