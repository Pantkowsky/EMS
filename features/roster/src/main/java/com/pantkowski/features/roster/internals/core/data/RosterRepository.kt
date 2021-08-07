package com.pantkowski.features.roster.internals.core.data

import com.pantkowski.domain.models.Employee
import io.reactivex.rxjava3.core.Single

interface RosterRepository {

    fun getEmployees(): Single<List<Employee>>
}
