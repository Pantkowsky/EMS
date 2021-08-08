package com.pantkowski.features.roster.internals.models

import com.pantkowski.features.base.mvi.core.models.MviResult

sealed class RosterResult : MviResult {

    sealed class InitialResult : RosterResult() {
        data class Success(val data: EmployeeData) : InitialResult()
        data class Failure(val error: Throwable) : InitialResult()
        object InFlight : InitialResult()
    }
}
