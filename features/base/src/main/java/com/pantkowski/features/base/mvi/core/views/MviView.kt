package com.pantkowski.features.base.mvi.core.views

import com.pantkowski.features.base.mvi.core.models.MviIntent
import com.pantkowski.features.base.mvi.core.models.MviState
import io.reactivex.rxjava3.core.Observable

interface MviView<I : MviIntent, S : MviState> {

    fun intents(): Observable<I>

    fun render(state: S)
}
