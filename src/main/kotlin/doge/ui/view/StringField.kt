package doge.ui.view

import doge.ui.model.StringViewModel
import tornadofx.bind
import tornadofx.stripWhitespace

class StringField(question : StringViewModel) : QuestionTextField(question){
    init {
        with(root){
            stripWhitespace()
            bind(question.stringProperty)
        }
    }
}