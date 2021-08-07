package com.pantkowski.features.roster.internals.models

import com.pantkowski.domain.models.Employee

data class EmployeeData(
    val count: Int,
    val employees: List<Employee>
)
