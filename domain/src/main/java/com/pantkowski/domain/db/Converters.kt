package com.pantkowski.domain.db

import androidx.room.TypeConverter
import java.util.UUID

internal class Converters {

    @TypeConverter
    fun toUUID(uuid: String): UUID =
        UUID.fromString(uuid)

    @TypeConverter
    fun fromUUID(uuid: UUID): String =
        uuid.toString()
}
