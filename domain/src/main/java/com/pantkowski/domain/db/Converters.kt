package com.pantkowski.domain.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pantkowski.domain.models.AddressType
import java.util.UUID

internal class Converters {

    @TypeConverter
    fun toUUID(uuid: String): UUID =
        UUID.fromString(uuid)

    @TypeConverter
    fun fromUUID(uuid: UUID): String =
        uuid.toString()

    @TypeConverter
    fun toAddress(json: String): Map<AddressType, String> {
        val addressType = object : TypeToken<Map<AddressType, String>>() {}.type
        return Gson().fromJson(json, addressType)
    }

    @TypeConverter
    fun fromAddress(data: Map<AddressType, String>): String {
        val gson = Gson()
        return gson.toJson(data)
    }
}
