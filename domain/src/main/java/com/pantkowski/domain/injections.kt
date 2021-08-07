package com.pantkowski.domain

import com.pantkowski.domain.db.EmployeeDB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val domain = module {
    single { EmployeeDB.getInstance(androidContext()) }
    single { get<EmployeeDB>().getEmployeeDao() }
}
