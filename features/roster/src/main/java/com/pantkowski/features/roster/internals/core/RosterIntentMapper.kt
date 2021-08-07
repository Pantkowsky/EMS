package com.pantkowski.features.roster.internals.core

import com.pantkowski.features.base.mvi.core.mappers.IntentMapper
import com.pantkowski.features.roster.internals.models.InitialAction
import com.pantkowski.features.roster.internals.models.InitialIntent
import com.pantkowski.features.roster.internals.models.RosterAction
import com.pantkowski.features.roster.internals.models.RosterIntent

class RosterIntentMapper : IntentMapper<RosterIntent, RosterAction> {

    override fun mapAsAction(intent: RosterIntent): RosterAction =
        when(intent) {
            is InitialIntent -> InitialAction
        }
}
