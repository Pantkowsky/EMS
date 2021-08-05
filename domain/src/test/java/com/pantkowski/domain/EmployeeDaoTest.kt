package com.pantkowski.domain

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.pantkowski.domain.db.EmployeeDB
import com.pantkowski.domain.db.EmployeeDao
import com.pantkowski.domain.models.AddressType
import com.pantkowski.domain.models.Employee
import com.pantkowski.domain.models.Gender
import org.junit.After
import org.junit.Before
import org.junit.Test

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

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(context, EmployeeDB::class.java).build()
        dao = db.getEmployeeDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `should add employee correctly`() {
        dao.add(employee)
            .test()
            .assertComplete()

        dao.getEmployees()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue { list ->
                val result = list.first()
                result.fullName == "Adam Pantkowski"
            }
    }
}
