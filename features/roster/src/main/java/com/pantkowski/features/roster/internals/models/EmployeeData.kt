package com.pantkowski.features.roster.internals.models

import com.pantkowski.domain.models.AddressType
import com.pantkowski.domain.models.Gender

data class EmployeeData(
    val count: Int,
    val employees: List<EmployeeModel>
)

data class EmployeeModel(
    private val nameArg: String,
    private val ageArg: Int,
    private val genderArg: Gender,
    private val addressArg: Map<AddressType, String>
) {
    val name: String
        get() = "Name: $nameArg"

    val age: String
        get() = "Age: $ageArg"

    val ageNumber: Int
        get() = ageArg

    val gender: String
        get() = "Gender: ${genderArg.describe()}"

    val addresses: String
        get() = StringBuilder().apply {
            append("Addresses:\n")
            takeIf { addressArg.containsKey(AddressType.HOME) }
                ?.append("Home: ${addressArg[AddressType.HOME]}\n")
            takeIf { addressArg.containsKey(AddressType.BUSINESS) }
                ?.append("Business: ${addressArg[AddressType.BUSINESS]}\n")
        }.toString()

    fun isValid() : Boolean =
        this.nameArg.isNotEmpty() &&
            this.ageArg in 18..99 &&
            this.addressArg.isNotEmpty()
}
