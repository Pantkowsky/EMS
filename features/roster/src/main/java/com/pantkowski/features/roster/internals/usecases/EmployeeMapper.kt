package com.pantkowski.features.roster.internals.usecases

import com.pantkowski.domain.models.Employee
import com.pantkowski.features.base.usecases.Mapper
import com.pantkowski.features.roster.internals.models.EmployeeData
import com.pantkowski.features.roster.internals.models.EmployeeModel

internal class EmployeeMapper : Mapper<List<Employee>, EmployeeData> {

    override fun mapTarget(target: List<Employee>): EmployeeData =
        EmployeeData(
            target.size,
            target.map { it.toModel() }
        )

    private fun Employee.toModel(): EmployeeModel =
        EmployeeModel(
            this.fullName,
            this.age,
            this.gender,
            this.address
        )
}
