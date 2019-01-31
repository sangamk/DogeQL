package ui.view.field

import ui.model.viewmodel.*

class QuestionFieldFactory {

    fun createQuestionField(question: QuestionViewModel) = when (question) {
        is StringViewModel -> StringField(question)
        is MoneyViewModel -> MoneyField(question)
        is IntegerViewModel -> IntegerField(question)
        is DecimalViewModel -> DecimalField(question)
        is BooleanViewModel -> CheckBox(question)
        is DateViewModel -> DateField(question)
        else -> throw IllegalArgumentException("${question.item.value.type} unsupported type")
    }
}