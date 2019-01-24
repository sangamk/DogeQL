package doge.ui.model

import doge.data.question.Question
import javafx.beans.property.SimpleStringProperty

class StringViewModel(question: Question) : QuestionModel(question) {

    var stringProperty = bind { SimpleStringProperty(item.value.stringValue.value) }

    override fun synchronizeDataModel() {
        item.value.stringValue.value = stringProperty.value
    }

    override fun setViewModelValue(question: Question) {
        stringProperty.value = question.value.stringValue.value
    }

}