package com.pantkowski.features.roster.internals

import android.os.Bundle
import android.view.View
import com.pantkowski.features.base.mvi.MviFragment
import com.pantkowski.features.base.mvi.MviViewModel
import com.pantkowski.features.roster.databinding.FragmentRosterBinding
import com.pantkowski.features.roster.internals.models.*
import com.pantkowski.features.roster.internals.models.InitialIntent
import io.reactivex.rxjava3.core.Observable

class RosterFragment : MviFragment<
    RosterIntent,
    RosterAction,
    RosterResult,
    RosterViewState,
    FragmentRosterBinding>() {

    override val viewModel: MviViewModel<RosterIntent, RosterAction, RosterResult, RosterViewState>
        get() = TODO("Not yet implemented")

    override val intents: List<Observable<out RosterIntent>>
        get() = listOf(initialIntent())

    override fun setViewBindings(): FragmentRosterBinding =
        FragmentRosterBinding.inflate(layoutInflater)

    override fun render(state: RosterViewState) {
        binding.textView.text = "$state"
    }

    override fun setupUiComponents(view: View, savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

    private fun initialIntent(): Observable<InitialIntent> =
        Observable.just(InitialIntent)
}
