package com.pantkowski.features.base.usecases

interface Mapper<I, O> {

    fun mapTo(target: I): O
}
