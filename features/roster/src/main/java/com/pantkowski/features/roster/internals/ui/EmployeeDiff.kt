package com.pantkowski.features.roster.internals.ui

import androidx.recyclerview.widget.DiffUtil
import com.pantkowski.features.roster.internals.models.EmployeeModel

class EmployeeDiff : DiffUtil.ItemCallback<EmployeeModel>() {

    override fun areContentsTheSame(oldItem: EmployeeModel, newItem: EmployeeModel): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: EmployeeModel, newItem: EmployeeModel): Boolean =
        oldItem.name == newItem.name
}
