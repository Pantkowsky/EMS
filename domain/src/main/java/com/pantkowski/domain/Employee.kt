package com.pantkowski.domain

data class Employee(
    val name: String,
    val lastName: String,
    val age: Int,
    val gender: Gender,
    val address: Map<AddressType, String>
) {
    val fullName: String
        get() = "$name $lastName"

    fun isValid() : Boolean =
        this.name.isNotEmpty() &&
            this.lastName.isNotEmpty() &&
            this.age in 18..99 &&
            this.address.isNotEmpty() &&
            this.address.keys.size in 1..2
}

enum class Gender {
    FEMALE,
    MALE,
    OTHER;

    fun describe(): String =
        when(this) {
            FEMALE -> "Female"
            MALE -> "Male"
            OTHER -> "Other"
        }
}

enum class AddressType {
    HOME,
    BUSINESS
}
