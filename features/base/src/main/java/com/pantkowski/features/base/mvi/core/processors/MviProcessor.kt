package com.pantkowski.features.base.mvi.core.processors

import com.pantkowski.features.base.mvi.core.models.MviAction
import com.pantkowski.features.base.mvi.core.models.MviResult
import io.reactivex.rxjava3.core.ObservableTransformer

interface MviProcessor<A : MviAction, R : MviResult> {
    val actionProcessor: ObservableTransformer<A, R>
}
