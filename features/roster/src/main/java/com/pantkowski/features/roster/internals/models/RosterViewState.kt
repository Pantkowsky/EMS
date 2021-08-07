package com.pantkowski.features.roster.internals.models

import com.pantkowski.features.base.mvi.core.models.MviState

data class RosterViewState(

    val isLoading: Boolean,
    val error: Throwable?
) : MviState {

    companion object {
        fun idle(): RosterViewState =
            RosterViewState(
                isLoading = false,
                error = null
            )
    }
}
