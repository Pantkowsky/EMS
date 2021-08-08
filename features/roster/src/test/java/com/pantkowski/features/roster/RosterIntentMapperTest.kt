package com.pantkowski.features.roster

import com.pantkowski.features.roster.internals.core.RosterIntentMapper
import com.pantkowski.features.roster.internals.models.RosterAction
import com.pantkowski.features.roster.internals.models.RosterIntent
import org.junit.jupiter.api.Test

class RosterIntentMapperTest {

    private val intentMapper: RosterIntentMapper = RosterIntentMapper()

    @Test
    fun `should map initial intents to correct action types`() {

        val intent = RosterIntent.InitialIntent
        val expected = RosterAction.InitialAction
        val given = intentMapper.mapAsAction(intent)

        assert(expected == given)
        assert(given is RosterAction.InitialAction)
    }
}
