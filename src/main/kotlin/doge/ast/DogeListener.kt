package doge.ast

class DogeListener {

//    val symbolTable = SymbolTable()
//    private val expressionBuilder = ExpressionBuilder()
//    private val formTreeBuilder = FormTreeBuilder(symbolTable)
//
//    override fun enterBlock(ctx: QuestionareLanguageParser.BlockContext?) {
//        if (!expressionBuilder.isEmpty()) {
//            val ifExpression = expressionBuilder.pop()
//            val result = symbolTable.registerSymbol(SymbolType.BOOLEAN, ifExpression)
//
//            formTreeBuilder.pushExpression(result.name, ifExpression.sourceLocation)
//        }
//    }
//
//
//    override fun exitIfStatement(ctx: QuestionareLanguageParser.IfStatementContext?) {
//        formTreeBuilder.build()
//    }
//
//    override fun exitQuestionStatement(ctx: QuestionareLanguageParser.QuestionStatementContext?) {
//        requireNotNull(ctx)
//        val context = ctx!!
//
//        val label = context.LIT_STRING().text
//        val name = context.NAME().text
//        val type = convertStringToType(context.TYPE().text)
//
//        var readOnly = false
//
//        symbolTable.registerSymbol(name, type)
//
//        if (!expressionBuilder.isEmpty()) {
//
//            val questionExpression = expressionBuilder.pop()
//
//            if (questionExpression.containsReference()) {
//                symbolTable.assign(name, type, questionExpression)
//                readOnly = true
//            } else {
//                symbolTable.assign(name, questionExpression.evaluate(symbolTable))
//            }
//        }
//
//        val questionNameLocation = SourceLocation(
//                context.NAME().symbol.line, context.NAME().symbol.charPositionInLine
//        )
//
//        val questionLabelLocation = SourceLocation(
//                context.LIT_STRING().symbol.line, context.LIT_STRING().symbol.charPositionInLine
//        )
//
//        val question = QuestionStatement(name, label, type.getDefaultInstance(), questionNameLocation, questionLabelLocation, readOnly)
//
//        formTreeBuilder.pushQuestion(question)
//    }
//
//    override fun exitExpression(ctx: QuestionareLanguageParser.ExpressionContext?) {
//        requireNotNull(ctx)
//
//        val context = ctx!!
//
//        context.NAME()?.let {
//            return pushReferenceExpression(it)
//        }
//
//        context.NOT()?.let {
//            return pushUnaryExpression(it, UnaryOperation.NEGATE)
//        }
//
//        context.MUL()?.let {
//            return pushBinaryExpression(it, BinaryOperation.MULTIPLY)
//        }
//
//        context.DIV()?.let {
//            return pushBinaryExpression(it, BinaryOperation.DIVIDE)
//        }
//
//        context.ADD()?.let {
//            return pushBinaryExpression(it, BinaryOperation.ADD)
//        }
//
//        context.SUB()?.let {
//            return pushBinaryExpression(it, BinaryOperation.SUBTRACT)
//        }
//
//        context.LT()?.let {
//            return pushBinaryExpression(it, BinaryOperation.LESS)
//        }
//
//        context.GT()?.let {
//            return pushBinaryExpression(it, BinaryOperation.GREATER)
//        }
//
//        context.LE()?.let {
//            return pushBinaryExpression(it, BinaryOperation.LESSEQUAL)
//        }
//
//        context.GE()?.let {
//            return pushBinaryExpression(it, BinaryOperation.GREATEQUAL)
//        }
//
//        context.EQUAL()?.let {
//            return pushBinaryExpression(it, BinaryOperation.EQUAL)
//        }
//
//        context.NOTEQUAL()?.let {
//            return pushBinaryExpression(it, BinaryOperation.NOTEQUAL)
//        }
//
//        context.AND()?.let {
//            return pushBinaryExpression(it, BinaryOperation.AND)
//        }
//
//        context.OR()?.let {
//            return pushBinaryExpression(it, BinaryOperation.OR)
//        }
//    }
//
//    override fun exitLiteral(ctx: QuestionareLanguageParser.LiteralContext?) {
//        requireNotNull(ctx)
//
//        val context = ctx!!
//
//        context.LIT_BOOLEAN()?.let {
//            return pushLiteralExpression(it, SymbolType.BOOLEAN)
//        }
//
//        context.LIT_INTEGER()?.let {
//            return pushLiteralExpression(it, SymbolType.INTEGER)
//        }
//
//        context.LIT_DECIMAL()?.let {
//            return pushLiteralExpression(it, SymbolType.DECIMAL)
//        }
//
//        context.LIT_STRING()?.let {
//            return pushLiteralExpression(it, SymbolType.STRING)
//        }
//
//        context.LIT_COLOR()?.let {
//            return pushLiteralExpression(it, SymbolType.COLOR)
//        }
//    }
//
//    private fun pushLiteralExpression(terminalNode: TerminalNode, type: SymbolType) {
//
//        val parsedValue = convertTerminalNodeToSymbol(type, terminalNode)
//
//        val sourceLocation = SourceLocation(terminalNode.symbol.line, terminalNode.symbol.charPositionInLine)
//
//        expressionBuilder.push(
//                LiteralExpression(parsedValue, sourceLocation)
//        )
//    }
//
//    private fun pushReferenceExpression(terminalNode: TerminalNode) {
//        val sourceLocation = SourceLocation(terminalNode.symbol.line, terminalNode.symbol.charPositionInLine)
//
//        expressionBuilder.push(
//                ReferenceExpression(terminalNode.text, SymbolType.UNDEFINED, sourceLocation)
//        )
//    }
//
//    private fun pushUnaryExpression(terminalNode: TerminalNode, operation: UnaryOperation) {
//        val value = expressionBuilder.pop()
//
//        val sourceLocation = SourceLocation(terminalNode.symbol.line, terminalNode.symbol.charPositionInLine)
//
//        expressionBuilder.push(
//                UnaryExpression(value, operation, sourceLocation)
//        )
//    }
//
//    private fun pushBinaryExpression(terminalNode: TerminalNode, operation: BinaryOperation) {
//        val right = expressionBuilder.pop()
//        val left = expressionBuilder.pop()
//
//        val sourceLocation = SourceLocation(terminalNode.symbol.line, terminalNode.symbol.charPositionInLine)
//
//        expressionBuilder.push(
//                BinaryExpression(left, right, operation, sourceLocation)
//        )
//    }
//
//    private fun convertStringToType(type: String) = when (type) {
//        "boolean" -> SymbolType.BOOLEAN
//        "int" -> SymbolType.INTEGER
//        "string" -> SymbolType.STRING
//        "money" -> SymbolType.MONEY
//        "decimal" -> SymbolType.DECIMAL
//        "date" -> SymbolType.DATE
//        "color" -> SymbolType.COLOR
//        else -> SymbolType.UNDEFINED
//    }
//
//    private fun convertTerminalNodeToSymbol(type: SymbolType, terminalNode: TerminalNode): BaseSymbolValue = when (type) {
//        SymbolType.BOOLEAN -> BooleanValue(terminalNode.text)
//        SymbolType.INTEGER -> IntegerValue(terminalNode.text)
//        SymbolType.DECIMAL -> DecimalValue(terminalNode.text)
//        SymbolType.STRING -> StringValue(terminalNode.text)
//        SymbolType.MONEY -> MoneyValue(terminalNode.text)
//        SymbolType.COLOR -> ColorValue(terminalNode.text)
//        else -> throw IllegalArgumentException("$type, unsupported type")
//    }
//
//    fun getParsedDogeLanguage(): Node = formTreeBuilder.build()
}