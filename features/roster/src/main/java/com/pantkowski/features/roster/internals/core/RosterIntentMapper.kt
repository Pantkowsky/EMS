package com.pantkowski.features.roster.internals.core

import com.pantkowski.features.base.mvi.core.mappers.IntentMapper
import com.pantkowski.features.roster.internals.models.*
import com.pantkowski.features.roster.internals.models.AddEmployeeIntent
import com.pantkowski.features.roster.internals.models.InitialAction
import com.pantkowski.features.roster.internals.models.InitialIntent

class RosterIntentMapper : IntentMapper<RosterIntent, RosterAction> {

    override fun mapAsAction(intent: RosterIntent): RosterAction =
        when(intent) {
            is InitialIntent -> InitialAction
            is AddEmployeeIntent -> AddEmployeeAction
            is DeleteEmployeeIntent -> DeleteEmployeeAction(intent.id)
        }
}
