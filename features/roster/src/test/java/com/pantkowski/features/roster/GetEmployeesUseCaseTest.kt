package com.pantkowski.features.roster

import com.pantkowski.features.roster.internals.core.data.RosterRepository
import com.pantkowski.features.roster.internals.models.EmployeeData
import com.pantkowski.features.roster.internals.usecases.EmployeeMapper
import com.pantkowski.features.roster.internals.usecases.GetEmployeesUseCase
import com.pantkowski.features.roster.util.testEmployees
import com.pantkowski.features.roster.util.testEmployeesMapped
import io.mockk.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetEmployeesUseCaseTest {

    private val repository: RosterRepository = mockk()
    private lateinit var mapper: EmployeeMapper
    private lateinit var useCase: GetEmployeesUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        this.mapper = EmployeeMapper()
        this.useCase = GetEmployeesUseCase(repository, mapper)
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `should get non-empty EmployeeData`() {

        every { repository.getEmployees() } returns Observable.just(testEmployees)

        val expected = EmployeeData(3, 3000, testEmployeesMapped)

        useCase.getEmployeeData()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue { data ->
                data.count == expected.count &&
                    data.employees.map { it.ageNumber }
                        .reduce(Int::plus) == expected.employees.map { it.ageNumber }.reduce(Int::plus)
            }

        verify(exactly = 1) {
            repository.getEmployees()
            mapper.mapTarget(testEmployees)
        }
    }

    @Test
    fun `should return empty EmployeeData when no records in database`() {

        every { repository.getEmployees() } returns Observable.just(listOf())

        useCase.getEmployeeData()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue { data ->
                data.employees.isEmpty() &&
                    data.count == 0
            }

        verify(exactly = 1) {
            repository.getEmployees()
            mapper.mapTarget(testEmployees)
        }
    }
}
