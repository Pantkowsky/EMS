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
import java.util.UUID

class RosterRepositoryImpl(private val dao: EmployeeDao) : RosterRepository {

    companion object {
        const val DEFAULT_RAISE: Long = 3000
    }

    override fun getEmployees(): Observable<List<Employee>> =
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

    private fun calculateRaise(employee: Employee): Long =
        employee.salary + DEFAULT_RAISE

    private val names = listOf(
        "Adam", "Robert", "Wojciech", "Piotr", "Bartosz", "Aneta",
        "Agnieszka", "Joanna", "Andrzej", "Mateusz", "Julia", "Patrycja", "Karolina", "Tadeusz",
        "Roman", "Natalia", "Amadeusz"
    )
    private val lastNames = listOf(
        "Nazwiskowski", "Testowski", "Projektowski", "Wojciechowski", "Robertowski",
        "Adamski", "Nowak", "Nowakowski", "Nowaczyk", "Rozmaity", "Niedzielny", "Sobotni", "Szybki"
    )
    private val ages = listOf(21, 24, 28, 33, 35, 30, 31, 29, 27, 25, 32, 40, 39, 38, 37, 20, 22, 23)
    private val salaries = listOf<Long>(15000, 18000, 20000, 22000, 17000, 18500, 19500, 19800, 25100, 25600, 23400)
    private val genders = listOf(Gender.MALE, Gender.FEMALE, Gender.OTHER)
    private val addressTypes = listOf(AddressType.HOME, AddressType.BUSINESS)
    private val addresses = listOf(
        "Cool Street, 1", "Engineering Street, 20", "Awesome Boulevard, 22", "Sunny Alley, 1",
        "Great Software, 25", "Best Boulevard, 28", "Doggo Street, 4", "Anonymous Street, 8", "Random Boulevard, 6", "Next Street, 11"
    )
}
