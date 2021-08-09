package com.pantkowski.features.roster.internals

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import com.pantkowski.features.base.mvi.MviFragment
import com.pantkowski.features.roster.databinding.FragmentRosterBinding
import com.pantkowski.features.roster.internals.models.*
import com.pantkowski.features.roster.internals.models.InitialIntent
import com.pantkowski.features.roster.internals.ui.RosterAdapter
import io.reactivex.rxjava3.core.Observable
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class RosterFragment : MviFragment<
    RosterIntent,
    RosterAction,
    RosterResult,
    RosterViewState,
    FragmentRosterBinding>() {

    override val viewModel: RosterViewModel by viewModel()
    private val adapter: RosterAdapter = RosterAdapter()

    override val intents: List<Observable<out RosterIntent>>
        get() = listOf(initialIntent(), addNewIntents())

    override fun setViewBindings(): FragmentRosterBinding =
        FragmentRosterBinding.inflate(layoutInflater)

    override fun render(state: RosterViewState) {
        when {
            state.hasErrors() -> showErrorMessage(state.error?.message)
            state.isLoading -> showLoading()
            state.hasData() -> renderUI(state.data!!)
        }
    }

    override fun setupUiComponents(view: View, savedInstanceState: Bundle?) {
        binding.rv.adapter = adapter
    }

    private fun showErrorMessage(msg: String?) {

    }

    private fun showLoading() {
        //TODO implement UI loader
    }

    private fun renderUI(data: EmployeeData) {
        binding.metadata.bind(data.count, data.salaries)
        binding.rv.itemAnimator = DefaultItemAnimator()
        this.adapter.setEmployees(data.employees)
    }

    private fun initialIntent(): Observable<InitialIntent> =
        Observable.just(InitialIntent)

    private fun addNewIntents() : Observable<AddEmployeeIntent> =
        binding.metadata.addClicks()
            .map { AddEmployeeIntent }
}
