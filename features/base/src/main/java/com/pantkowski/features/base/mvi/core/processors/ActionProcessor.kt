package com.pantkowski.features.base.mvi.core.processors

import com.pantkowski.features.base.mvi.core.models.MviAction
import com.pantkowski.features.base.mvi.core.models.MviResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer

abstract class ActionProcessor<A : MviAction, R : MviResult> : MviProcessor<A, R> {

    abstract val processors: Map<ObservableTransformer<out A, R>, Class<out A>>

    @Suppress("UNCHECKED_CAST")
    private val mappedProcessors: Map<ObservableTransformer<in A, R>, Class<out A>>
        get() = processors.mapKeys { it.key as ObservableTransformer<in A, R> }


    override val actionProcessor: ObservableTransformer<A, R> =
        ObservableTransformer<A, R> { actions ->
            actions.publish { shared ->
                Observable.merge(mappedProcessors.entries.map { shared.ofType(it.value).compose(it.key) })
                    .mergeWith(
                        shared.filter { action ->
                            processors.values.none { it.isInstance(action) }
                        }.flatMap { action ->
                            Observable.error(IllegalArgumentException("Unknown type: $action"))
                        }
                    )
            }
        }
}
