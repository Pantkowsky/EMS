package com.pantkowski.features.roster.internals.usecases

import com.pantkowski.domain.models.Employee
import com.pantkowski.features.base.usecases.UseCase
import com.pantkowski.features.roster.internals.core.data.RosterRepository
import com.pantkowski.features.roster.internals.models.EmployeeData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

internal class GetEmployeesUseCase(
    private val source: RosterRepository,
    private val mapper: EmployeeMapper
    ) : UseCase<List<Employee>, EmployeeData>(mapper) {

    fun getEmployeeData(): Observable<EmployeeData> =
        source.getEmployees()
            .flatMap { Observable.just(it.reversed()) }
            .map(mapper::mapTarget)

    fun addEmployee() : Completable =
        source.addEmployee()
}
