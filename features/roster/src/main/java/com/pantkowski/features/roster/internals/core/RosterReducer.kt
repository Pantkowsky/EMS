package com.pantkowski.features.roster.internals.core

import com.pantkowski.features.base.mvi.core.reducers.MviReducer
import com.pantkowski.features.roster.internals.models.*
import com.pantkowski.features.roster.internals.models.AddEmployeeResult
import com.pantkowski.features.roster.internals.models.DeleteEmployeeResult
import com.pantkowski.features.roster.internals.models.GiveRaiseResult
import com.pantkowski.features.roster.internals.models.InitialResult
import com.pantkowski.features.roster.internals.models.SortResult

class RosterReducer : MviReducer<RosterViewState, RosterResult>() {

    override fun apply(oldState: RosterViewState, result: RosterResult): RosterViewState =
        when (result) {
            is InitialResult -> when (result) {
                is RosterResult.InitialResult.Success ->
                    oldState.copy(
                        isLoading = false,
                        error = null,
                        data = result.data
                    )
                is RosterResult.InitialResult.Failure ->
                    oldState.copy(
                        isLoading = false,
                        error = result.error
                    )
                is RosterResult.InitialResult.InFlight ->
                    oldState.copy(
                        isLoading = true,
                        error = null
                    )
            }
            is AddEmployeeResult -> when (result) {
                is RosterResult.AddEmployeeResult.Success ->
                    oldState.copy(
                        isLoading = false,
                        error = null,
                        data = result.data
                    )
                is RosterResult.AddEmployeeResult.Failure ->
                    oldState.copy(
                        isLoading = false,
                        error = result.error
                    )
                is RosterResult.AddEmployeeResult.InFlight ->
                    oldState.copy(
                        isLoading = true,
                        error = null
                    )
            }
            is DeleteEmployeeResult -> when (result) {
                is RosterResult.DeleteEmployeeResult.Success ->
                    oldState.copy(
                        isLoading = false,
                        error = null,
                        data = result.data
                    )
                is RosterResult.DeleteEmployeeResult.Failure ->
                    oldState.copy(
                        isLoading = false,
                        error = result.error
                    )
                is RosterResult.DeleteEmployeeResult.InFlight ->
                    oldState.copy(
                        isLoading = true,
                        error = null
                    )
            }
            is GiveRaiseResult -> when (result) {
                is RosterResult.GiveRaiseResult.Success ->
                    oldState.copy(
                        isLoading = false,
                        error = null,
                        data = result.data
                    )
                is RosterResult.GiveRaiseResult.Failure ->
                    oldState.copy(
                        isLoading = false,
                        error = result.error
                    )
                is RosterResult.GiveRaiseResult.InFlight ->
                    oldState.copy(
                        isLoading = true,
                        error = null
                    )
            }
            is SortResult -> when(result) {
                is RosterResult.SortResult.Success ->
                    oldState.copy(
                        isLoading = false,
                        error = null,
                        data = sorted(result.data, result.sortType)
                    )
                is RosterResult.SortResult.Failure ->
                    oldState.copy(
                        isLoading = false,
                        error = result.error
                    )
                is RosterResult.SortResult.InFlight ->
                    oldState.copy(
                        isLoading = true,
                        error = null
                    )
            }
        }

    private fun sorted(data: EmployeeData, sortType: SortType) : EmployeeData =
        with(data) {
            when(sortType) {
                SortType.AGE -> copy(employees = data.employees.sortedBy { it.age })
                SortType.NAME -> copy(employees = data.employees.sortedBy { it.name })
                SortType.SALARY -> copy(employees = data.employees.sortedBy { it.salary })
            }
        }
}
