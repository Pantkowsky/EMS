package com.pantkowski.features.roster.internals.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.pantkowski.features.roster.R

class MetadataView : ConstraintLayout {

    constructor(context: Context): super(context) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet) {
        init(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int): super(context, attributeSet, defStyle) {
        init(context, attributeSet, defStyle)
    }

    private lateinit var total: TextView

    fun bind(total: Int) {
        this.total.text = String.format(context.getString(R.string.total_employees), total)
    }

    private fun init(context: Context, attributeSet: AttributeSet? = null, defStyle: Int = 0) =
        inflate(context, R.layout.view_metadata, this).also {
            this.total = it.findViewById(R.id.total)
        }
}
