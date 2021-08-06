package com.pantkowski.domain.db

import androidx.room.*
import com.pantkowski.domain.models.Employee
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(employee: Employee) : Completable

    @Update
    fun edit(employee: Employee) : Completable

    @Delete
    fun remove(employee: Employee) : Completable

    @Query("SELECT * FROM employee")
    fun getEmployees() : Single<List<Employee>>
}
