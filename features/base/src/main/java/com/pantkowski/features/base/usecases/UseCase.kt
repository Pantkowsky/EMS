package com.pantkowski.features.base.usecases

open class UseCase<I, O>(private val mapper: Mapper<I, O>) {

    fun get(target: I): O =
        mapper.mapTarget(target)
}
