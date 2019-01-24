package doge.ui.model

import doge.data.question.Question
import javafx.beans.property.SimpleIntegerProperty

class IntegerViewModel(question: Question) : QuestionModel(question) {

    var integerProperty = bind { SimpleIntegerProperty(item.value.integerValue.value) }

    override fun setViewModelValue(question: Question) {
        integerProperty.value = question.value.integerValue.value
    }

    override fun synchronizeDataModel() {
        item.value.integerValue.value = integerProperty.value.toInt()
    }

}