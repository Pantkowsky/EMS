package com.pantkowski.features.base.mvi.core.reducers

import com.pantkowski.features.base.mvi.core.models.MviResult
import com.pantkowski.features.base.mvi.core.models.MviState
import io.reactivex.rxjava3.functions.BiFunction

abstract class MviReducer<S : MviState, R : MviResult> : BiFunction<S, R, S>
