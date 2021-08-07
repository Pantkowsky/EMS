package com.pantkowski.features.roster.internals.core.data

import com.pantkowski.domain.db.EmployeeDao
import com.pantkowski.domain.models.Employee
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RosterRepositoryImpl(private val dao: EmployeeDao) : RosterRepository {

    override fun getEmployees() : Single<List<Employee>> =
        dao.getEmployees()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
