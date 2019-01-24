package doge.ui.view

import doge.ui.model.*

class ViewFactory() {

    fun createQuestionField(question : QuestionModel) : QuestionField {
        return when(question){
            is StringViewModel -> StringField(question)
            is MoneyViewModel -> MoneyField(question)
            is IntegerViewModel -> IntegerField(question)
            is DecimalViewModel -> DecimalField(question)
            is BooleanViewModel -> CheckBox(question)
            else -> throw IllegalArgumentException("${question.item.value.type} unsupported type")
        }
    }
}