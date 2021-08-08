package com.pantkowski.features.roster

import com.pantkowski.features.roster.internals.core.RosterProcessor
import com.pantkowski.features.roster.internals.models.EmployeeData
import com.pantkowski.features.roster.internals.models.InitialAction
import com.pantkowski.features.roster.internals.models.RosterResult
import com.pantkowski.features.roster.internals.usecases.GetEmployeesUseCase
import com.pantkowski.features.roster.util.testEmployees
import com.pantkowski.features.roster.util.testEmployeesMapped
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.*

class RosterProcessorTest {

    private val useCase: GetEmployeesUseCase = mockk()
    private lateinit var processor: RosterProcessor

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        this.processor = RosterProcessor(useCase)
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Nested
    @DisplayName("Processing initial actions")
    inner class InitialActionProcessor {

        @Test
        fun `should return successsful result on InitialAction`() {

            val data = EmployeeData(3, testEmployeesMapped)
            every { useCase.getEmployeeData() } returns Single.just(data)

            Observable.just(InitialAction)
                .compose(processor.actionProcessor)
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValueAt(0, RosterResult.InitialResult.InFlight)
                .assertValueAt(1, RosterResult.InitialResult.Success(data))
        }

        @Test
        fun `should return failure result on InitialAction when error thrown`() {

            val error = UninitializedPropertyAccessException("database has not been initialized")
            every { useCase.getEmployeeData() } returns Single.error(error)

            Observable.just(InitialAction)
                .compose(processor.actionProcessor)
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValueAt(0, RosterResult.InitialResult.InFlight)
                .assertValueAt(1, RosterResult.InitialResult.Failure(error))
        }
    }
}
