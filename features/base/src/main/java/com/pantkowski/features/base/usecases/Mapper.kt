package com.pantkowski.features.base.usecases

interface Mapper<I, O> {

    fun mapTarget(target: I): O
}
