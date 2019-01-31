package ui.model.viewmodel

import javafx.beans.property.SimpleBooleanProperty
import ui.model.domain.Question

class BooleanViewModel(question: Question) : QuestionViewModel(question) {

    var booleanValue = bind { SimpleBooleanProperty(item.value.booleanValue.value) }

    override fun setViewModelValue(question: Question) {
        booleanValue.value = question.value.booleanValue.value
    }

    override fun synchronizeDataModel() {
        item.value.booleanValue.value = booleanValue.value
    }

}