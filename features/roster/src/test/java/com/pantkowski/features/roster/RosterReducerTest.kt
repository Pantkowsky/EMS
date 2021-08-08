package com.pantkowski.features.roster

import com.pantkowski.features.roster.internals.core.RosterReducer
import com.pantkowski.features.roster.internals.models.RosterResult
import com.pantkowski.features.roster.internals.models.RosterViewState
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class RosterReducerTest {

    private val reducer: RosterReducer = RosterReducer()

    @Nested
    @DisplayName("InitialResult type")
    inner class InitialResult {

        @Test
        fun `should create loading state on InFlight result type`() {
            val viewState = RosterViewState.idle()
            val result = RosterResult.InitialResult.InFlight
            val given = reducer.apply(viewState, result)

            assert(given.isLoading)
            assert(given.error == null)
        }

        @Test
        fun `should create error state on Failure result type`() {
            val viewState = RosterViewState.idle()
            val result = RosterResult.InitialResult.Failure(RuntimeException("runtime error"))
            val given = reducer.apply(viewState, result)

            assert(given.isLoading.not())
            assert(given.error is RuntimeException)
            assert(given.error != null)
            assert(given.error?.message == "runtime error")
        }
    }
}
