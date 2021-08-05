package com.pantkowski.domain.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pantkowski.domain.employee
import com.pantkowski.domain.models.AddressType
import com.pantkowski.domain.models.Employee
import com.pantkowski.domain.models.Gender
import java.util.concurrent.Executors

@Database(entities = [Employee::class], version = 1)
@TypeConverters(Converters::class)
internal abstract class EmployeeDB : RoomDatabase() {

    abstract fun getEmployeeDao(): EmployeeDao

    companion object {
        private const val DB_NAME = "db_employees"
        @Volatile
        private var instance: EmployeeDB? = null

        fun getInstance(context: Context): EmployeeDB =
            instance ?: synchronized(this) {
                instance ?: createDb(context).apply { instance = this }
            }

        private fun createDb(context: Context) =
            Room.databaseBuilder(
                context,
                EmployeeDB::class.java,
                DB_NAME
            ).build()

        private fun prepopulate(context: Context) =
            Executors.newSingleThreadExecutor().execute {
                getInstance(context).getEmployeeDao().add(employee)
            }

        private val employee: Employee = employee {
            name("Adam")
            lastName("Pantkowski")
            age(30)
            gender(Gender.MALE)
            address(AddressType.HOME, "Home Street, 10")
        }
    }
}
