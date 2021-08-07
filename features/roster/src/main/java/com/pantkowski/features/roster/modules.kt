package com.pantkowski.features.roster

import com.pantkowski.features.roster.internals.RosterViewModel
import com.pantkowski.features.roster.internals.core.RosterProcessor
import com.pantkowski.features.roster.internals.core.data.RosterRepository
import com.pantkowski.features.roster.internals.core.data.RosterRepositoryImpl
import com.pantkowski.features.roster.internals.usecases.EmployeeMapper
import com.pantkowski.features.roster.internals.usecases.GetEmployeesUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val roster = module {
    viewModel { RosterViewModel(get()) }
    single { RosterProcessor(get()) }
    single { GetEmployeesUseCase(get(), get()) }
    single { EmployeeMapper() }
    single<RosterRepository> { RosterRepositoryImpl(get()) }
}

val rosterModules = listOf(roster)
