package com.pantkowski.domain.models

internal class EmployeeBuilder {
    private var nameArg: String = ""
    private var lastNameArg: String = ""
    private var ageArg: Int = 0
    private var genderArg: Gender? = null
    private var addressMap: LinkedHashMap<AddressType, String> = linkedMapOf()

    fun name(name: String) : EmployeeBuilder =
        this.apply { nameArg = name }

    fun lastName(lastName: String) : EmployeeBuilder =
        this.apply { lastNameArg = lastName }

    fun age(age: Int) : EmployeeBuilder =
        this.apply { ageArg = age }

    fun gender(gender: Gender) : EmployeeBuilder =
        this.apply { genderArg = gender }

    fun address(type: AddressType, address: String) : EmployeeBuilder =
        this.apply {
            addressMap[type] = address
        }

    fun build() : Employee =
        Employee(
            name = nameArg,
            lastName = lastNameArg,
            age = ageArg,
            gender = genderArg ?: Gender.OTHER,
            address = addressMap
        )
}
