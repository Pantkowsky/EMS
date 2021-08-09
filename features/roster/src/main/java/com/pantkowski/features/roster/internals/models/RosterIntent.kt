package com.pantkowski.features.roster.internals.models

import com.pantkowski.features.base.mvi.core.models.MviIntent

sealed class RosterIntent : MviIntent {

    object InitialIntent : RosterIntent()

    object AddEmployeeIntent : RosterIntent()
}
