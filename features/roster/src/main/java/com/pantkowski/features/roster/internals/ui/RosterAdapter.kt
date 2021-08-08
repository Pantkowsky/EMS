package com.pantkowski.features.roster.internals.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pantkowski.features.roster.R
import com.pantkowski.features.roster.internals.models.EmployeeModel

class RosterAdapter : RecyclerView.Adapter<EmployeeHolder>() {

    private var employees: List<EmployeeModel> = listOf()

    override fun getItemCount(): Int =
        employees.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeHolder =
        EmployeeHolder(parent.inflate(R.layout.holder_employee))

    override fun onBindViewHolder(holder: EmployeeHolder, position: Int) =
        holder.bind(employees[position])

    fun setEmployees(data: List<EmployeeModel>) {
        this.employees = data
        this.notifyItemRangeInserted(0, data.size)
    }
}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}
