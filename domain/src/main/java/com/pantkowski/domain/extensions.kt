package com.pantkowski.domain

internal inline fun employee(
    creator: EmployeeBuilder.() -> Unit
): Employee {
    return EmployeeBuilder().apply(creator).build()
}
