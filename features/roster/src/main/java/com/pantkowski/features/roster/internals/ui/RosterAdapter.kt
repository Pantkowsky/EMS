package com.pantkowski.features.roster.internals.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.pantkowski.features.roster.R
import com.pantkowski.features.roster.internals.models.EmployeeModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.UUID

class RosterAdapter : RecyclerView.Adapter<RosterAdapter.EmployeeHolder>() {

    private val deleteSubject: PublishSubject<UUID> = PublishSubject.create()
    private val raiseSubject: PublishSubject<UUID> = PublishSubject.create()

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

    fun adapterDeletes(): Observable<UUID> =
        deleteSubject

    fun adapterRaises(): Observable<UUID> =
        raiseSubject

    inner class EmployeeHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val name: TextView = view.findViewById(R.id.name)
        private val gender: TextView = view.findViewById(R.id.gender)
        private val age: TextView = view.findViewById(R.id.age)
        private val salary: TextView = view.findViewById(R.id.salary)
        private val address: TextView = view.findViewById(R.id.address)
        private val deleteButton: ImageButton = view.findViewById(R.id.delete)
        private val raiseButton: ImageButton = view.findViewById(R.id.raise)
        private lateinit var id: UUID

        init {
            this.deleteButton.setOnClickListener { deleteSubject.onNext(id) }
            this.raiseButton.setOnClickListener { raiseSubject.onNext(id) }
        }

        fun bind(model: EmployeeModel) {
            this.id = model.id
            this.name.text = model.name
            this.gender.text = model.gender
            this.age.text = model.age
            this.salary.text = model.salary
            this.address.text = model.addresses
        }
    }
}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}
