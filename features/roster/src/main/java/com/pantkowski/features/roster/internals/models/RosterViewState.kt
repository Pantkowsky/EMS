package com.pantkowski.features.roster.internals.models

import com.pantkowski.features.base.mvi.core.models.MviState

data class RosterViewState(
    val isLoading: Boolean,
    val error: Throwable?,
    val data: EmployeeData?
) : MviState {

    fun hasErrors(): Boolean =
        this.error != null

    fun hasData(): Boolean =
        this.data != null

    companion object {
        fun idle(): RosterViewState =
            RosterViewState(
                isLoading = false,
                error = null,
                data = null
            )
    }
}
