package com.pantkowski.features.roster.internals.core

import com.pantkowski.features.base.mvi.core.processors.ActionProcessor
import com.pantkowski.features.roster.internals.models.InitialResult
import com.pantkowski.features.roster.internals.models.RosterAction
import com.pantkowski.features.roster.internals.models.RosterResult
import com.pantkowski.features.roster.internals.usecases.GetEmployeesUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer

private typealias Transformer = ObservableTransformer<out RosterAction, RosterResult>
private typealias ActionType = Class<out RosterAction>

internal class RosterProcessor(private val useCase: GetEmployeesUseCase) : ActionProcessor<RosterAction, RosterResult>() {

    override val processors: Map<Transformer, ActionType>
        get() = mapOf(
            initialProcessor to RosterAction.InitialAction::class.java
        )

    private val initialProcessor: Transformer
        get() = Transformer { actions ->
            actions.flatMap { action ->
                Observable.just(action)
                    .flatMapSingle { useCase.getEmployeeData() }
                    .map { RosterResult.InitialResult.Success(it) }
                    .cast(InitialResult::class.java)
                    .onErrorReturn { RosterResult.InitialResult.Failure(it) }
                    .startWithItem(RosterResult.InitialResult.InFlight)
            }
        }
}
