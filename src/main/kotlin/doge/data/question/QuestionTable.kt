package doge.data.question

import doge.common.Name
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class QuestionTable {

    private val table = HashMap<Name, Question>()

    fun register(name: Name, question: Question) {
        table[name] = question
    }

    fun print(){
        table.forEach { identifier, question ->
            println("ID: $identifier, QuestionStatement: $question ")
        }
    }

    fun findDuplicateLabels(): List<String> {

        val uniqueLabels = HashSet<String>()

        val duplicateLabels = ArrayList<String>()

        table.forEach{
            _, question ->
            if (!uniqueLabels.add(question.label)){
                duplicateLabels.add(question.label)
            }
        }

        duplicateLabels.forEach{
            label ->
            println("Warning duplicate label: $label")
        }

        return duplicateLabels
    }
}