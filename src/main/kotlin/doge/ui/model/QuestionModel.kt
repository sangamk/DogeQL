package doge.ui.model

import doge.data.question.Question
import doge.ui.controller.DogeController
import tornadofx.ItemViewModel

abstract class QuestionModel(question: Question) : ItemViewModel<Question>(question) {

    var readOnly = question.readOnly

    private val dogeController: DogeController by inject()
    private val questionFormModel: QuestionFormModel by inject()

    override fun onCommit() {
        synchronizeDataModel()
    }

    fun update() {
        // Only update if there are changes
        // Added this check to remove unnecessary updates
        if (dirtyProperties.size > 0 && dirtyProperties.first() != null) {
            synchronizeDataModel()
            dogeController.updateQuestion(item)
            questionFormModel.load()
        }
    }

    abstract fun setViewModelValue(question: Question)

    abstract fun synchronizeDataModel()
}


