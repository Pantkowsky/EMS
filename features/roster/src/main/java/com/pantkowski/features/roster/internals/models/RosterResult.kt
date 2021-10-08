package com.pantkowski.features.roster.internals.models

import com.pantkowski.features.base.mvi.core.models.MviResult

sealed class RosterResult : MviResult {

    sealed class InitialResult : RosterResult() {
        data class Success(val data: EmployeeData) : InitialResult()
        data class Failure(val error: Throwable) : InitialResult()
        object InFlight : InitialResult()
    }

    sealed class AddEmployeeResult : RosterResult() {
        data class Success(val data: EmployeeData) : AddEmployeeResult()
        data class Failure(val error: Throwable) : AddEmployeeResult()
        object InFlight : AddEmployeeResult()
    }

    sealed class DeleteEmployeeResult : RosterResult() {
        data class Success(val data: EmployeeData) : DeleteEmployeeResult()
        data class Failure(val error: Throwable) : DeleteEmployeeResult()
        object InFlight : DeleteEmployeeResult()
    }

    sealed class GiveRaiseResult : RosterResult() {
        data class Success(val data: EmployeeData) : GiveRaiseResult()
        data class Failure(val error: Throwable) : GiveRaiseResult()
        object InFlight : GiveRaiseResult()
    }

    sealed class SortResult : RosterResult() {
        data class Success(val data: EmployeeData, val sortType: SortType) : SortResult()
        data class Failure(val error: Throwable) : SortResult()
        object InFlight : SortResult()
    }
}
