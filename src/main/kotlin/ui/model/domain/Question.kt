package ui.model.domain

import ql.common.Name
import ql.data.value.BaseSymbolValue

data class Question(
        val name: Name,
        val label: String,
        val value: BaseSymbolValue,
        val readOnly: Boolean,
        val visible: Boolean
)