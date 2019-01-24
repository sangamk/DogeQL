package doge.ui.model

import doge.data.question.Question
import doge.ui.controller.DogeController
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.ItemViewModel

class QuestionFormModel : ItemViewModel<QuestionModel>() {

    var questions: ObservableList<QuestionModel> = FXCollections.observableArrayList<QuestionModel>()

    private var dataQuestions = listOf<Question>()

    private val dogeController: DogeController by inject()

    override fun onCommit() {
        questions.forEach { x ->
            x.commit()
        }
    }

    fun load() {
        runAsync {
            dogeController.getQuestions()
        } ui {
            updateViewModel(it)
            dataQuestions = it
        }
    }


    private fun updateViewModel(newDataQuestions: List<Question>) {
        val modelFactory = ViewModelFactory()

        val toAdd = newDataQuestions.filter { question ->
            question !in dataQuestions || question.readOnly
        }

        questions.removeIf { question ->
            question.item !in newDataQuestions || question.item.readOnly
        }

        // Only add new questions, leave old questions as is
        toAdd.forEach { question ->
            questions.add(newDataQuestions.indexOf(question), modelFactory.createUiQuestionModel(question))
        }

    }
}