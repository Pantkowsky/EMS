package com.pantkowski.features.base.mvi

import com.pantkowski.features.base.mvi.core.mappers.IntentMapper
import com.pantkowski.features.base.mvi.core.models.MviAction
import com.pantkowski.features.base.mvi.core.models.MviIntent
import com.pantkowski.features.base.mvi.core.models.MviResult
import com.pantkowski.features.base.mvi.core.models.MviState
import com.pantkowski.features.base.mvi.core.processors.MviProcessor
import com.pantkowski.features.base.mvi.core.reducers.MviReducer
import com.pantkowski.features.base.mvi.core.viewmodels.MviBaseViewModel
import com.pantkowski.features.base.viewmodels.BaseViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.subjects.PublishSubject

abstract class MviViewModel<
    I : MviIntent,
    A : MviAction,
    R : MviResult,
    S : MviState
    > : BaseViewModel(), MviBaseViewModel<I, S> {

    abstract val initialIntentType: Class<out I>
    abstract val processor: MviProcessor<A, R>
    abstract val reducer: MviReducer<S, R>
    abstract val mapper: IntentMapper<I, A>

    abstract fun idleState(): S

    private val intentsSubject: PublishSubject<I> =
        PublishSubject.create()
    private val statesObservable: Observable<S> by lazy {
        composeState()
    }
    private val intentsFilter: ObservableTransformer<I ,I>
        get() = ObservableTransformer { intents ->
            intents.publish { shared ->
                Observable.merge(
                    shared.ofType(initialIntentType).take(1),
                    shared.notOfType(initialIntentType)
                )
            }
        }

    override fun intents(intents: Observable<I>) =
        intents.subscribe(intentsSubject)

    override fun states(): Observable<S> =
        statesObservable

    private fun composeState(): Observable<S> =
        intentsSubject
            .compose(intentsFilter)
            .map(mapper::mapAsAction)
            .compose(processor.actionProcessor)
            .scan(idleState(), reducer)
            .distinctUntilChanged()
            .replay(1)
            .autoConnect()
}
