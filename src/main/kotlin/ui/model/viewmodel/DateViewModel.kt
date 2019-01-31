package ui.model.viewmodel

import javafx.beans.property.SimpleObjectProperty
import ui.model.domain.Question
import java.time.LocalDate

class DateViewModel(question: Question) : QuestionViewModel(question) {

    val dateProperty = bind { SimpleObjectProperty<LocalDate>(question.value.dateValue.value) }

    override fun setViewModelValue(question: Question) {
        dateProperty.value = question.value.dateValue.value
    }

    override fun synchronizeDataModel() {
        item.value.dateValue.value = dateProperty.value
    }

}