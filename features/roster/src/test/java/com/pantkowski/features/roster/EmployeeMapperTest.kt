package com.pantkowski.features.roster

import com.pantkowski.domain.models.Employee
import com.pantkowski.features.roster.internals.models.EmployeeData
import com.pantkowski.features.roster.internals.usecases.EmployeeMapper
import com.pantkowski.features.roster.util.adam
import com.pantkowski.features.roster.util.anna
import com.pantkowski.features.roster.util.robert
import org.junit.jupiter.api.Test

class EmployeeMapperTest {

    private val mapper: EmployeeMapper = EmployeeMapper()

    @Test
    fun `should map EmployeeData correctly`() {
        val employees = listOf(adam, anna, robert)

        val given = mapper.mapTarget(employees)
        val expected = EmployeeData(3, employees)
        val givenValid = given.employees
            .map { it.isValid() }
            .reduce { acc, b -> if (b) acc else b }
        val expectedValid = expected.employees
            .map { it.isValid() }
            .reduce { acc, b -> if (b) acc else b }

        assert(expected.count == given.count)
        assert(expected.employees.size == given.employees.size)
        assert(expectedValid == givenValid)
        assert(expected.employees.map { it.age }.reduce(Int::plus)
            == given.employees.map { it.age }.reduce(Int::plus))
    }

    @Test
    fun `should map to empty EmployeeData`() {
        val employees = listOf<Employee>()

        val given = mapper.mapTarget(employees)
        val expected = EmployeeData(0, listOf())

        assert(expected.count == given.count)
        assert(given.employees.isEmpty())
    }
}
