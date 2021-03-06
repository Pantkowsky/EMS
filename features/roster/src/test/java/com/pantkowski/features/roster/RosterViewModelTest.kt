package com.pantkowski.features.roster

import com.pantkowski.features.roster.internals.RosterViewModel
import com.pantkowski.features.roster.internals.core.RosterProcessor
import com.pantkowski.features.roster.internals.core.data.RosterRepository
import com.pantkowski.features.roster.internals.models.RosterIntent
import com.pantkowski.features.roster.internals.usecases.EmployeeMapper
import com.pantkowski.features.roster.internals.usecases.GetEmployeesUseCase
import com.pantkowski.features.roster.util.RxImmediateSchedulerRule
import com.pantkowski.features.roster.util.testEmployees
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(RxImmediateSchedulerRule::class)
class RosterViewModelTest {

    private val repo: RosterRepository = mockk()
    private lateinit var useCase: GetEmployeesUseCase
    private lateinit var processor: RosterProcessor
    private lateinit var viewModel: RosterViewModel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        this.useCase = GetEmployeesUseCase(repo, EmployeeMapper())
        this.processor = RosterProcessor(useCase)
        this.viewModel = RosterViewModel(processor)
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `should create data state`() {
        every { repo.getEmployees() } returns Observable.just(testEmployees)

        viewModel.intents(Observable.just(RosterIntent.InitialIntent))
        viewModel.states()
            .test()
            .assertNoErrors()
            .assertValue { state ->
                !state.isLoading &&
                    state.error == null &&
                    state.data != null &&
                    state.data?.count == 3
            }
    }

    @Test
    fun `should create error state`() {
        val error = UninitializedPropertyAccessException("database has not been initialized")
        every { repo.getEmployees() } returns Observable.error(error)

        viewModel.intents(Observable.just(RosterIntent.InitialIntent))
        viewModel.states()
            .test()
            .assertNoErrors()
            .assertValue { state ->
                !state.isLoading &&
                    state.error != null &&
                    state.data == null
            }
    }
}
