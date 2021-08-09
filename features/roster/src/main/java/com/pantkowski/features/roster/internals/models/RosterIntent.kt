package com.pantkowski.features.roster.internals.models

import com.pantkowski.features.base.mvi.core.models.MviIntent
import java.util.UUID

sealed class RosterIntent : MviIntent {

    object InitialIntent : RosterIntent()

    object AddEmployeeIntent : RosterIntent()

    data class DeleteEmployeeIntent(val id: UUID) : RosterIntent()

    data class GiveRaiseIntent(val id: UUID) : RosterIntent()
}
