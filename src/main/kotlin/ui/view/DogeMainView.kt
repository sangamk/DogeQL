package ui.view

import javafx.stage.FileChooser
import tornadofx.*
import ui.controller.DogeController
import ui.view.fragment.FormFragment
import ui.view.fragment.InfoFragment
import ui.view.fragment.StylizedFormFragment

class DogeMainView : View() {

    private val controller: DogeController by inject()

    private val minHeight = 800.0
    private val minWidth = 500.0

    private var fragment: Fragment = FormFragment()
    private var infoFragment = InfoFragment()

    override val root = vbox {
        minHeight = this@DogeMainView.minHeight
        minWidth = this@DogeMainView.minWidth

        menubar {
            menu("Import") {
                item("Language").action { loadDogeQl() }
                item("Style").action { loadStyle() }
            }
            menu("Info") {
                item("Show messages").action { addInfoScreen() }
            }
        }

        add(fragment)
    }

    private fun addInfoScreen() {
        with(fragment.root as Drawer) {

            item("Info Messages") {
                add(infoFragment)
            }
        }
    }

    private fun loadDogeQl() {
        val files = chooseFile(
                "Select file",
                arrayOf(FileChooser.ExtensionFilter("Doge questionnaire file", "*.doge"))
        )

        if (files.isNotEmpty()) {
            controller.loadQuestionnaire(files.first())
        }
    }

    private fun loadStyle() {
        val files = chooseFile(
                "Select file",
                arrayOf(FileChooser.ExtensionFilter("Doge questionnaire style file", "*.shiba"))
        )

        if (files.isNotEmpty()) {
            controller.loadStyle(files.first())
            updateFragment(StylizedFormFragment())
        }
    }

    private fun updateFragment(newFragment: Fragment) {
        fragment.replaceWith(newFragment)
        fragment = newFragment
    }

}
