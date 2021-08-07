package com.pantkowski.features.base.mvi.core.viewmodels

import com.pantkowski.features.base.mvi.core.models.MviIntent
import com.pantkowski.features.base.mvi.core.models.MviState
import io.reactivex.rxjava3.core.Observable

interface MviBaseViewModel<I : MviIntent, S : MviState> {

    fun intents(intents: Observable<I>)

    fun states(): Observable<S>
}
