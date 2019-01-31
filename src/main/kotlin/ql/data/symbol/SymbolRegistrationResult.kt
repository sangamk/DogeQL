package ql.data.symbol

import ql.data.value.BaseSymbolValue

sealed class SymbolRegistrationResult {
    class Registered : SymbolRegistrationResult()
    class AlreadyRegistered : SymbolRegistrationResult()
    class AlreadyRegisteredTypeMismatch(val value: BaseSymbolValue) : SymbolRegistrationResult()
}
