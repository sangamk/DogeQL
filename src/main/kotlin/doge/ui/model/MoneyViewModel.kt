package doge.ui.model

import doge.data.question.Question
import javafx.beans.property.SimpleObjectProperty
import java.math.BigDecimal

class MoneyViewModel(question: Question) : QuestionModel(question) {

    var moneyProperty = bind { SimpleObjectProperty<BigDecimal>(item.value.decimalValue.value) }

    override fun setViewModelValue(question: Question) {
        moneyProperty.value = question.value.decimalValue.value
    }

    override fun synchronizeDataModel() {
        item.value.decimalValue.value = moneyProperty.value
    }
}