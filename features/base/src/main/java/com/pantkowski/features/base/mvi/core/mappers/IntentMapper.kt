package com.pantkowski.features.base.mvi.core.mappers

interface IntentMapper<I, A> {

    fun mapAsAction(intent: I): A
}
