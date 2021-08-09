package com.pantkowski.domain

import com.pantkowski.domain.models.Employee
import com.pantkowski.domain.models.EmployeeBuilder

inline fun employee(
    creator: EmployeeBuilder.() -> Unit
): Employee {
    return EmployeeBuilder().apply(creator).build()
}
