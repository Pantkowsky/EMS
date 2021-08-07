package com.pantkowski.features.base.usecases

class UseCase<I, O>(private val mapper: Mapper<I, O>) {

    fun get(target: I): O =
        mapper.mapTo(target)
}
