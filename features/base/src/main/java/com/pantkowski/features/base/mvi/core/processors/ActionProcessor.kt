package com.pantkowski.features.base.mvi.core.processors

import com.pantkowski.features.base.mvi.core.models.MviAction
import com.pantkowski.features.base.mvi.core.models.MviResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer

abstract class ActionProcessor<A : MviAction, R : MviResult> : MviProcessor<A, R> {

    abstract val processors: Map<ObservableTransformer<A, R>, Class<out A>>

    override val actionProcessor: ObservableTransformer<A, R> =
        ObservableTransformer<A, R> { actions ->
            actions.publish { shared ->
                Observable.fromIterable(processors.entries)
                    .flatMap { shared.ofType(it.value).compose(it.key) }
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
