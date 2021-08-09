package com.pantkowski.features.roster

import com.pantkowski.features.roster.internals.core.RosterIntentMapper
import com.pantkowski.features.roster.internals.models.RosterAction
import com.pantkowski.features.roster.internals.models.RosterIntent
import org.junit.jupiter.api.Test
import java.util.*

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

    @Test
    fun `should map add employee intents to correct action types`() {

        val intent = RosterIntent.AddEmployeeIntent
        val expected = RosterAction.AddEmployeeAction
        val given = intentMapper.mapAsAction(intent)

        assert(expected == given)
        assert(given is RosterAction.AddEmployeeAction)
    }

    @Test
    fun `should map delete employee intents to correct action types`() {

        val uuid = UUID.randomUUID()
        val intent = RosterIntent.DeleteEmployeeIntent(uuid)
        val expected = RosterAction.DeleteEmployeeAction(uuid)
        val given = intentMapper.mapAsAction(intent)

        assert(expected == given)
        assert(given is RosterAction.DeleteEmployeeAction)
    }

    @Test
    fun `should map give raise intents to correct action types`() {

        val uuid = UUID.randomUUID()
        val intent = RosterIntent.GiveRaiseIntent(uuid)
        val expected = RosterAction.GiveRaiseAction(uuid)
        val given = intentMapper.mapAsAction(intent)

        assert(expected == given)
        assert(given is RosterAction.GiveRaiseAction)
    }
}
