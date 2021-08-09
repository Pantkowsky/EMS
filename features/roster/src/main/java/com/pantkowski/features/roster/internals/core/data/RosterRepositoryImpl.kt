package com.pantkowski.features.roster.internals.core.data

import com.pantkowski.domain.db.EmployeeDao
import com.pantkowski.domain.employee
import com.pantkowski.domain.models.AddressType
import com.pantkowski.domain.models.Employee
import com.pantkowski.domain.models.Gender
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class RosterRepositoryImpl(private val dao: EmployeeDao) : RosterRepository {

    override fun getEmployees() : Observable<List<Employee>> =
        dao.getEmployees()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun addEmployee(): Completable =
        dao.add(createEmployee())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun deleteEmployee(id: UUID): Completable =
        dao.remove(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    private fun createEmployee() = employee {
        name(UUID.randomUUID().toString())
        lastName("Added")
        age(30)
        salary(10000)
        gender(Gender.MALE)
        address(AddressType.BUSINESS, "Business Street, 15")
    }
}
