package com.pantkowski.features.roster.util

import com.pantkowski.domain.employee
import com.pantkowski.domain.models.AddressType
import com.pantkowski.domain.models.Gender
import com.pantkowski.features.roster.internals.models.EmployeeModel

val adam = employee {
    name("Adam")
    lastName("Pantkowski")
    age(30)
    salary(1000)
    gender(Gender.MALE)
    address(AddressType.HOME, "Home Street, 1")
}

val anna = employee {
    name("Anna")
    lastName("Nazwiskowa")
    age(25)
    salary(1000)
    gender(Gender.FEMALE)
    address(AddressType.HOME, "Home Boulevard, 122")
}

val robert = employee {
    name("Robert")
    lastName("Robertowski")
    age(33)
    salary(1000)
    gender(Gender.OTHER)
    address(AddressType.BUSINESS, "Business Street, 11")
}

val testEmployees = listOf(adam, anna, robert)
val testEmployeesMapped = testEmployees.map { EmployeeModel(it.fullName, it.age, it.salary, it.gender, it.address) }
