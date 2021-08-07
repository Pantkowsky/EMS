package com.pantkowski.features.roster.internals

import com.pantkowski.features.base.mvi.MviViewModel
import com.pantkowski.features.base.mvi.core.mappers.IntentMapper
import com.pantkowski.features.base.mvi.core.reducers.MviReducer
import com.pantkowski.features.roster.internals.core.RosterIntentMapper
import com.pantkowski.features.roster.internals.core.RosterProcessor
import com.pantkowski.features.roster.internals.core.RosterReducer
import com.pantkowski.features.roster.internals.models.RosterAction
import com.pantkowski.features.roster.internals.models.RosterIntent
import com.pantkowski.features.roster.internals.models.RosterResult
import com.pantkowski.features.roster.internals.models.RosterViewState

internal class RosterViewModel(override val processor: RosterProcessor) :
    MviViewModel<RosterIntent, RosterAction, RosterResult, RosterViewState>() {

    override val initialIntentType: Class<out RosterIntent>
        get() = RosterIntent.InitialIntent::class.java

    override val reducer: MviReducer<RosterViewState, RosterResult>
        get() = RosterReducer()

    override val mapper: IntentMapper<RosterIntent, RosterAction>
        get() = RosterIntentMapper()

    override fun idleState(): RosterViewState =
        RosterViewState.idle()
}
