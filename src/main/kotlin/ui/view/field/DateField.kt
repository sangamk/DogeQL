package ui.view.field

import tornadofx.bind
import tornadofx.datepicker
import ui.model.viewmodel.DateViewModel

class DateField(dateViewModel: DateViewModel) : QuestionField(dateViewModel) {

    override val root = datepicker {
        bind(dateViewModel.dateProperty)
        attachListener(valueProperty())
    }
}