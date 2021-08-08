package com.pantkowski.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "employees")
data class Employee(
    @ColumnInfo val name: String,
    @ColumnInfo val lastName: String,
    @ColumnInfo val age: Int,
    @ColumnInfo val salary: Long,
    @ColumnInfo val gender: Gender,
    @ColumnInfo val address: Map<AddressType, String>,
    @PrimaryKey val id: UUID = UUID.nameUUIDFromBytes("$name $lastName".encodeToByteArray())
) {
    val fullName: String
        get() = "$name $lastName"

    fun isValid(): Boolean =
        this.name.isNotEmpty() &&
            this.lastName.isNotEmpty() &&
            this.age in 18..99 &&
            this.salary > 0 &&
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
