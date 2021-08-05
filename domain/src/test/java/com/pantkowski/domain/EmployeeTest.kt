package com.pantkowski.domain

import com.pantkowski.domain.models.AddressType
import com.pantkowski.domain.models.Gender
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EmployeeTest {

    @Test
    fun `should create valid employee`() {
        val employee = employee {
            name("Adam")
            lastName("Pantkowski")
            age(30)
            gender(Gender.MALE)
            address(AddressType.HOME, "Street Name 30")
        }

        assertTrue {
            employee.fullName == "Adam Pantkowski" &&
                employee.age == 30 &&
                employee.gender.describe() == "Male" &&
                employee.address.isNotEmpty() &&
                employee.address.containsKey(AddressType.HOME) &&
                employee.address.size == 1 &&
                employee.isValid()
        }
    }

    @Test
    fun `should create employee with multiple addresses`() {
        val employee = employee {
            name("Adam")
            lastName("Pantkowski")
            age(30)
            gender(Gender.MALE)
            address(AddressType.HOME, "Street Name 30")
            address(AddressType.BUSINESS, "Business Street, 1")
        }

        assertTrue {
            employee.isValid() &&
                employee.address.size == 2 &&
                employee.address[AddressType.BUSINESS] == "Business Street, 1"
        }
    }

    @Test
    fun `should create invalid employee with missing name`() {
        val employee = employee {
            age(30)
            gender(Gender.MALE)
            address(AddressType.HOME, "Street Name 30")
        }

        assertFalse { employee.isValid() }
    }

    @Test
    fun `should create invalid employee with missing age`() {
        val employee = employee {
            name("Adam")
            lastName("Pantkowski")
            gender(Gender.MALE)
            address(AddressType.HOME, "Street Name 30")
        }

        assertFalse { employee.isValid() }
    }

    @Test
    fun `should create invalid employee with missing address`() {
        val employee = employee {
            name("Adam")
            lastName("Pantkowski")
            age(30)
            gender(Gender.MALE)
        }

        assertFalse { employee.isValid() }
    }
}
