package com.pantkowski.features.roster.internals

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import com.pantkowski.features.base.mvi.MviFragment
import com.pantkowski.features.roster.R
import com.pantkowski.features.roster.databinding.FragmentRosterBinding
import com.pantkowski.features.roster.internals.models.*
import com.pantkowski.features.roster.internals.models.AddEmployeeIntent
import com.pantkowski.features.roster.internals.models.DeleteEmployeeIntent
import com.pantkowski.features.roster.internals.models.GiveRaiseIntent
import com.pantkowski.features.roster.internals.models.InitialIntent
import com.pantkowski.features.roster.internals.models.SortIntent
import com.pantkowski.features.roster.internals.ui.RosterAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

internal class RosterFragment : MviFragment<
    RosterIntent,
    RosterAction,
    RosterResult,
    RosterViewState,
    FragmentRosterBinding>() {

    override val viewModel: RosterViewModel by viewModel()
    private val adapter: RosterAdapter = RosterAdapter()
    private val scrollSubject: PublishSubject<Unit> = PublishSubject.create()
    private val menuSubject: PublishSubject<RosterIntent> = PublishSubject.create()

    override val intents: List<Observable<out RosterIntent>>
        get() = listOf(initialIntent(), deleteIntents(), raiseIntents(), menuSubject)

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
        observeScrollTicks()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_roster, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.addButton -> menuSubject.onNext(AddEmployeeIntent)
            R.id.sortButton -> menuSubject.onNext(SortIntent(1))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showErrorMessage(msg: String?) {
        Toast.makeText(this.context, "Error is: $msg", Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        /**
         * Here should come any logic that should handle data loading state
         */
    }

    private fun renderUI(data: EmployeeData) {
        binding.metadata.bind(data.count, data.salaries)
        binding.rv.itemAnimator = DefaultItemAnimator()
        this.adapter.setEmployees(data.employees) {
            scrollSubject.onNext(Unit)
        }
    }

    private fun initialIntent(): Observable<InitialIntent> =
        Observable.just(InitialIntent)

    private fun deleteIntents(): Observable<DeleteEmployeeIntent> =
        adapter.adapterDeletes()
            .map { DeleteEmployeeIntent(it) }

    private fun raiseIntents(): Observable<GiveRaiseIntent> =
        adapter.adapterRaises()
            .map { GiveRaiseIntent(it) }

    /**
     * Hacky hack to animate list changes. This is a smelly code
     * and should be changed.
     */
    private fun observeScrollTicks() {
        scrollSubject.delay(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { binding.rv.smoothScrollToPosition(0) }
            ).addTo(disposableBag)
    }
}
