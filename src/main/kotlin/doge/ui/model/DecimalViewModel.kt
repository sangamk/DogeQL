package doge.ui.model

import doge.data.question.Question
import javafx.beans.property.SimpleObjectProperty
import java.math.BigDecimal

class DecimalViewModel(question: Question) : QuestionModel(question){

    var decimalProperty = bind { SimpleObjectProperty<BigDecimal>(item.value.decimalValue.value) }

    override fun setViewModelValue(question: Question) {
        decimalProperty.value = question.value.decimalValue.value
    }

    override fun synchronizeDataModel() {
        item.value.decimalValue.value = decimalProperty.value
    }

}