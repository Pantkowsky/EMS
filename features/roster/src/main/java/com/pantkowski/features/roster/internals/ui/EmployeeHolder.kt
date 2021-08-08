package com.pantkowski.features.roster.internals.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pantkowski.features.roster.R
import com.pantkowski.features.roster.internals.models.EmployeeModel

class EmployeeHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val name: TextView = view.findViewById(R.id.name)
    private val gender: TextView = view.findViewById(R.id.gender)
    private val age: TextView = view.findViewById(R.id.age)
    private val address: TextView = view.findViewById(R.id.address)

    fun bind(model: EmployeeModel) {
        this.name.text = model.name
        this.gender.text = model.gender
        this.age.text = model.age
        this.address.text = model.addresses
    }
}
