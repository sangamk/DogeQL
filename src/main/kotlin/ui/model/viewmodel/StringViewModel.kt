package ui.model.viewmodel

import javafx.beans.property.SimpleStringProperty
import ui.model.domain.Question

class StringViewModel(question: Question) : QuestionViewModel(question) {

    var stringProperty = bind { SimpleStringProperty(item.value.stringValue.value) }

    override fun synchronizeDataModel() {
        item.value.stringValue.value = stringProperty.value
    }

    override fun setViewModelValue(question: Question) {
        stringProperty.value = question.value.stringValue.value
    }

}