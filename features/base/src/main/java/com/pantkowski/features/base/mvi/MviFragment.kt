package com.pantkowski.features.base.mvi

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.pantkowski.features.base.fragments.BaseFragment
import com.pantkowski.features.base.mvi.core.models.MviAction
import com.pantkowski.features.base.mvi.core.models.MviIntent
import com.pantkowski.features.base.mvi.core.models.MviResult
import com.pantkowski.features.base.mvi.core.models.MviState
import com.pantkowski.features.base.mvi.core.views.MviView
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo

abstract class MviFragment<
    I : MviIntent,
    A : MviAction,
    R : MviResult,
    S : MviState,
    V : ViewBinding> : BaseFragment<V>(), MviView<I, S> {

    abstract override fun setViewBindings(): V
    abstract override fun render(state: S)

    abstract val viewModel: MviViewModel<I, A, R, S>
    abstract val intents: List<Observable<out I>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.bindViewModel()
    }

    override fun intents(): Observable<I> =
        Observable.merge(intents)

    private fun bindViewModel() {
        viewModel.states()
            .subscribe(this::render)
            .addTo(disposableBag)
        viewModel.intents(intents())
    }
}
