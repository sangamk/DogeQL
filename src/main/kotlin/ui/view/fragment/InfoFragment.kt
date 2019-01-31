package ui.view.fragment

import tornadofx.View
import tornadofx.listview
import ui.controller.DogeController

class InfoFragment : View() {

    private val controller: DogeController by inject()

    override val root = listview(controller.infoMessages)
}