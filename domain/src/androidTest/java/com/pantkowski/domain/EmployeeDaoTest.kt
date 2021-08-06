package com.pantkowski.domain

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.pantkowski.domain.db.EmployeeDB
import com.pantkowski.domain.db.EmployeeDao
import com.pantkowski.domain.models.AddressType
import com.pantkowski.domain.models.Employee
import com.pantkowski.domain.models.Gender
import org.junit.jupiter.api.*

class EmployeeDaoTest {

    private lateinit var db: EmployeeDB
    private lateinit var dao: EmployeeDao
    private val employee: Employee = employee {
        name("Adam")
        lastName("Pantkowski")
        age(30)
        gender(Gender.MALE)
        address(AddressType.HOME, "Home Street, 10")
    }

    @BeforeEach
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(context, EmployeeDB::class.java).build()
        dao = db.getEmployeeDao()
    }

    @AfterEach
    fun tearDown() {
        db.close()
    }

    @Nested
    @DisplayName("ADD operations")
    inner class AddEmployee {

        @Test
        fun shouldAddEmployee() {
            dao.add(employee)
                .test()
                .assertComplete()

            dao.getEmployees()
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue { result ->
                    with(result.first()) {
                        fullName == "Adam Pantkowski" &&
                            age == 30 &&
                            gender == Gender.MALE &&
                            address.size == 1 &&
                            address.containsKey(AddressType.HOME) &&
                            address[AddressType.HOME] == "Home Street, 10"
                    }
                }
        }

        @Test
        fun shouldReplaceEmployee_onConflict() {
            dao.add(employee)
                .test()
                .assertComplete()
                .assertNoErrors()

            val conflictingEmployee = employee.copy(name = "Tadeusz")

            dao.add(conflictingEmployee)
                .test()
                .assertComplete()
                .assertNoErrors()

            dao.getEmployees()
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue { it.size == 1 }
                .assertValue { result ->
                    with(result.first()) {
                        fullName == "Tadeusz Pantkowski" &&
                            age == 30 &&
                            gender == Gender.MALE &&
                            address.size == 1 &&
                            address.containsKey(AddressType.HOME) &&
                            address[AddressType.HOME] == "Home Street, 10"
                    }
                }
        }
    }

    @Nested
    @DisplayName("EDIT operations")
    inner class UpdateEmployee {

        @Test
        fun shouldUpdateEmployee() {
            dao.add(employee)
                .test()
                .assertComplete()
                .assertNoErrors()

            val updated = employee.copy(name = "Tadeusz", age = 35, address = mapOf(AddressType.BUSINESS to "Business Street, 5"))

            dao.edit(updated)
                .test()
                .assertComplete()
                .assertNoErrors()

            dao.getEmployees()
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue { it.size == 1 }
                .assertValue { result ->
                    with(result.first()) {
                        fullName == "Tadeusz Pantkowski" &&
                            age == 35 &&
                            gender == Gender.MALE &&
                            address.size == 1 &&
                            address.containsKey(AddressType.BUSINESS) &&
                            address[AddressType.BUSINESS] == "Business Street, 5"
                    }
                }
        }
    }

    @Nested
    @DisplayName("DELETE operations")
    inner class DeleteEmployee {

        @Test
        fun shouldDeleteEmployee() {
            dao.getEmployees()
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue { it.isEmpty() }

            dao.add(employee)
                .test()
                .assertComplete()
                .assertNoErrors()

            dao.getEmployees()
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue { it.size == 1 }

            dao.remove(employee)
                .test()
                .assertComplete()
                .assertNoErrors()

            dao.getEmployees()
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue { it.isEmpty() }
        }
    }

}
