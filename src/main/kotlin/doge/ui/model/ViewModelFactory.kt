package doge.ui.model

import doge.data.question.Question
import doge.data.question.SymbolType

class ViewModelFactory {

    fun createUiQuestionModel(question : Question) : QuestionModel {

        return when(question.value.type){
            SymbolType.BOOLEAN -> BooleanViewModel(question)
            SymbolType.INTEGER -> IntegerViewModel(question)
            SymbolType.DECIMAL -> DecimalViewModel(question)
            SymbolType.STRING -> StringViewModel(question)
            SymbolType.MONEY -> MoneyViewModel(question)
            SymbolType.COLOR -> TODO()
            SymbolType.DATE -> TODO()
            SymbolType.UNDEFINED -> TODO()
        }
    }
}