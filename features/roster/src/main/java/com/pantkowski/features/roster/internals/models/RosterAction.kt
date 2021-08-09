package com.pantkowski.features.roster.internals.models

import com.pantkowski.features.base.mvi.core.models.MviAction
import java.util.UUID

sealed class RosterAction : MviAction {

    object InitialAction : RosterAction()

    object AddEmployeeAction : RosterAction()

    data class DeleteEmployeeAction(val id: UUID) : RosterAction()

    data class GiveRaiseAction(val id: UUID) : RosterAction()
}
