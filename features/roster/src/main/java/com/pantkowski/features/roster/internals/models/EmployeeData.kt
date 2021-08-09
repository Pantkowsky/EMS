package com.pantkowski.features.roster.internals.models

import com.pantkowski.domain.models.AddressType
import com.pantkowski.domain.models.Gender
import java.util.*

data class EmployeeData(
    val count: Int,
    val salaries: Long,
    val employees: List<EmployeeModel>
)

data class EmployeeModel(
    private val idArg: UUID,
    private val nameArg: String,
    private val ageArg: Int,
    private val salaryArg: Long,
    private val genderArg: Gender,
    private val addressArg: Map<AddressType, String>
) {
    val id: UUID
        get() = idArg

    val name: String
        get() = "Name: $nameArg"

    val age: String
        get() = "Age: $ageArg"

    val salary: String
        get() = "Salary: $salaryArg"

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
            this.salaryArg > 0 &&
            this.addressArg.isNotEmpty()
}
