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

    companion object {
        const val DEFAULT_RAISE: Long = 3000
    }

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

    override fun raiseSalary(id: UUID): Completable =
        dao.getEmployee(id)
            .map { calculateRaise(it) }
            .flatMapCompletable { dao.edit(id, it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    private fun createEmployee() = employee {
        name(names.random())
        lastName(lastNames.random())
        age(ages.random())
        salary(salaries.random())
        gender(genders.random())
        address(addressTypes.random(), addresses.random())
    }

    private fun calculateRaise(employee: Employee) : Long =
        employee.salary + DEFAULT_RAISE

    private val names = listOf("Adam", "Robert", "Wojciech", "Piotr", "Bartosz", "Aneta", "Agnieszka")
    private val lastNames = listOf("Nazwiskowski", "Testowski", "Projektowski", "Wojciechowski", "Robertowski", "Adamski")
    private val ages = listOf(21, 24, 28, 33, 35, 30, 31, 29, 27, 25, 32)
    private val salaries = listOf<Long>(15000, 18000, 20000, 22000, 17000, 18500, 19500, 198000, 25100, 256000, 23400)
    private val genders = listOf(Gender.MALE, Gender.FEMALE, Gender.OTHER)
    private val addressTypes = listOf(AddressType.HOME, AddressType.BUSINESS)
    private val addresses = listOf("Cool Street, 1", "Engineering Street, 20", "Awesome Boulevard, 22", "Sunny Alley, 1",
        "Great Software, 25", "Best Boulevard, 28", "Doggo Street, 4")
}
