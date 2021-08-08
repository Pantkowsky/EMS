package com.pantkowski.features.roster.internals.core

import com.pantkowski.features.base.mvi.core.reducers.MviReducer
import com.pantkowski.features.roster.internals.models.InitialResult
import com.pantkowski.features.roster.internals.models.RosterResult
import com.pantkowski.features.roster.internals.models.RosterViewState

class RosterReducer : MviReducer<RosterViewState, RosterResult>() {

    override fun apply(oldState: RosterViewState, result: RosterResult): RosterViewState =
        when(result) {
            is InitialResult -> when(result) {
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
        }
}
