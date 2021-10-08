package com.pantkowski.features.roster.internals.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.jakewharton.rxbinding4.view.clicks
import com.pantkowski.features.roster.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class MetadataView : ConstraintLayout {

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(context, attributeSet, defStyle) {
        init(context, attributeSet, defStyle)
    }

    private lateinit var totalEmployees: TextView
    private lateinit var salaries: TextView

    fun bind(total: Int, salaries: Long) {
        this.totalEmployees.text = String.format(context.getString(R.string.total_employees), total)
        this.salaries.text = String.format(context.getString(R.string.total_salaries), salaries)
    }

    private fun init(context: Context, attributeSet: AttributeSet? = null, defStyle: Int = 0) =
        inflate(context, R.layout.view_metadata, this).also {
            this.totalEmployees = it.findViewById(R.id.total)
            this.salaries = it.findViewById(R.id.salarySum)
        }
}
