package com.pantkowski.features.roster.internals.usecases

import com.pantkowski.domain.models.Employee
import com.pantkowski.features.base.usecases.UseCase
import com.pantkowski.features.roster.internals.core.data.RosterRepository
import com.pantkowski.features.roster.internals.models.EmployeeData
import io.reactivex.rxjava3.core.Single

internal class GetEmployeesUseCase(
    private val source: RosterRepository,
    private val mapper: EmployeeMapper
    ) : UseCase<List<Employee>, EmployeeData>(mapper) {

    fun getEmployeeData(): Single<EmployeeData> =
        source.getEmployees()
            .map(mapper::mapTarget)
}
