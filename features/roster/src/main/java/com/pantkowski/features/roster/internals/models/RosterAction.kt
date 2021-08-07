package com.pantkowski.features.roster.internals.models

import com.pantkowski.features.base.mvi.core.models.MviAction

sealed class RosterAction : MviAction {

    object InitialAction : RosterAction()
}
