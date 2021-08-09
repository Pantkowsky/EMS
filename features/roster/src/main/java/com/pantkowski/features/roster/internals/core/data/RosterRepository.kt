package com.pantkowski.features.roster.internals.core.data

import com.pantkowski.domain.models.Employee
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import java.util.UUID

interface RosterRepository {

    fun getEmployees(): Observable<List<Employee>>

    fun addEmployee() : Completable

    fun deleteEmployee(id: UUID) : Completable
}
