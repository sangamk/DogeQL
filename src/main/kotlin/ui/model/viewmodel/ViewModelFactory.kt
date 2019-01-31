package ui.model.viewmodel

import ql.data.symbol.SymbolType
import ui.model.domain.Question

class ViewModelFactory {

    fun createQuestionViewModel(question: Question) = when (question.value.type) {
        SymbolType.BOOLEAN -> BooleanViewModel(question)
        SymbolType.INTEGER -> IntegerViewModel(question)
        SymbolType.DECIMAL -> DecimalViewModel(question)
        SymbolType.STRING -> StringViewModel(question)
        SymbolType.MONEY -> MoneyViewModel(question)
        SymbolType.DATE -> DateViewModel(question)
        else -> throw IllegalArgumentException("${question.value.type} unsupported type")
    }
}