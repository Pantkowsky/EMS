package com.pantkowski.features.roster.util

import com.pantkowski.domain.employee
import com.pantkowski.domain.models.AddressType
import com.pantkowski.domain.models.Gender

val adam = employee {
    name("Adam")
    lastName("Pantkowski")
    age(30)
    gender(Gender.MALE)
    address(AddressType.HOME, "Home Street, 1")
}

val anna = employee {
    name("Anna")
    lastName("Nazwiskowa")
    age(25)
    gender(Gender.FEMALE)
    address(AddressType.HOME, "Home Boulevard, 122")
}

val robert = employee {
    name("Robert")
    lastName("Robertowski")
    age(33)
    gender(Gender.OTHER)
    address(AddressType.BUSINESS, "Business Street, 11")
}
