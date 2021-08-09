package com.pantkowski.domain.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pantkowski.domain.models.Employee
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import java.util.UUID

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSync(employee: Employee)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(employee: Employee): Completable

    @Update
    fun edit(employee: Employee): Completable

    @Query("DELETE FROM employees WHERE id = :id")
    fun remove(id: UUID): Completable

    @Query("SELECT * FROM employees")
    fun getEmployees(): Observable<List<Employee>>
}
