package com.pantkowski.features.roster.internals.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.pantkowski.features.roster.R
import com.pantkowski.features.roster.internals.models.EmployeeModel

class RosterAdapter : RecyclerView.Adapter<EmployeeHolder>() {

    private val diff: AsyncListDiffer<EmployeeModel> =
        AsyncListDiffer(this, EmployeeDiff())

    override fun getItemCount(): Int =
        diff.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeHolder =
        EmployeeHolder(parent.inflate(R.layout.holder_employee))

    override fun onBindViewHolder(holder: EmployeeHolder, position: Int) =
        holder.bind(diff.currentList[position])

    fun setEmployees(data: List<EmployeeModel>, block: () -> Unit) {
        diff.submitList(data)
        block()
    }
}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}
