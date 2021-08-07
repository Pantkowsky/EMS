package com.pantkowski.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Employee(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo val name: String,
    @ColumnInfo val lastName: String,
    @ColumnInfo val age: Int,
    @ColumnInfo val gender: Gender,
    @ColumnInfo val address: Map<AddressType, String>
) {
    val fullName: String
        get() = "$name $lastName"

    fun isValid(): Boolean =
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
        when (this) {
            FEMALE -> "Female"
            MALE -> "Male"
            OTHER -> "Other"
        }
}

enum class AddressType {
    HOME,
    BUSINESS
}
