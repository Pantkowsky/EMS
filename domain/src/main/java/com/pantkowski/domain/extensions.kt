package com.pantkowski.domain

import com.pantkowski.domain.models.Employee
import com.pantkowski.domain.models.EmployeeBuilder

internal inline fun employee(
    creator: EmployeeBuilder.() -> Unit
): Employee {
    return EmployeeBuilder().apply(creator).build()
}
