package com.pantkowski.features.roster.internals.core

import com.pantkowski.features.base.mvi.core.processors.ActionProcessor
import com.pantkowski.features.roster.internals.models.InitialResult
import com.pantkowski.features.roster.internals.models.RosterAction
import com.pantkowski.features.roster.internals.models.RosterResult
import com.pantkowski.features.roster.internals.usecases.GetEmployeesUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.kotlin.zipWith

private typealias Transformer = ObservableTransformer<out RosterAction, RosterResult>
private typealias ActionType = Class<out RosterAction>

internal class RosterProcessor(private val useCase: GetEmployeesUseCase) : ActionProcessor<RosterAction, RosterResult>() {

    override val processors: Map<Transformer, ActionType>
        get() = mapOf(
            initialProcessor to RosterAction.InitialAction::class.java,
            addEmployeeProcessor to RosterAction.AddEmployeeAction::class.java,
            deleteEmployeeProcessor to RosterAction.DeleteEmployeeAction::class.java,
            salaryRaiseProcessor to RosterAction.GiveRaiseAction::class.java,
            sortProcessor to RosterAction.SortAction::class.java
        )

    private val initialProcessor: Transformer
        get() = Transformer { actions ->
            actions.flatMap {
                useCase.getEmployeeData()
                    .map { RosterResult.InitialResult.Success(it) }
                    .cast(InitialResult::class.java)
                    .onErrorReturn { RosterResult.InitialResult.Failure(it) }
                    .startWithItem(RosterResult.InitialResult.InFlight)
            }
        }

    private val addEmployeeProcessor: Transformer
        get() = Transformer { actions ->
            actions.flatMap {
                useCase.addEmployee()
                    .andThen(useCase.getEmployeeData())
                    .map { RosterResult.AddEmployeeResult.Success(it) }
                    .cast(RosterResult.AddEmployeeResult::class.java)
                    .onErrorReturn { RosterResult.AddEmployeeResult.Failure(it) }
                    .startWithItem(RosterResult.AddEmployeeResult.InFlight)
            }
        }

    private val deleteEmployeeProcessor: Transformer
        get() = Transformer { actions ->
            actions.flatMap { action ->
                Observable.just(action)
                    .cast(RosterAction.DeleteEmployeeAction::class.java)
                    .flatMapCompletable { useCase.deleteEmployee(it.id) }
                    .andThen(useCase.getEmployeeData())
                    .map { RosterResult.DeleteEmployeeResult.Success(it) }
                    .cast(RosterResult.DeleteEmployeeResult::class.java)
                    .onErrorReturn { RosterResult.DeleteEmployeeResult.Failure(it) }
                    .startWithItem(RosterResult.DeleteEmployeeResult.InFlight)
            }
        }

    private val salaryRaiseProcessor: Transformer
        get() = Transformer { actions ->
            actions.flatMap { action ->
                Observable.just(action)
                    .cast(RosterAction.GiveRaiseAction::class.java)
                    .flatMapCompletable { useCase.raiseSalary(it.id) }
                    .andThen(useCase.getEmployeeData())
                    .map { RosterResult.GiveRaiseResult.Success(it) }
                    .cast(RosterResult.GiveRaiseResult::class.java)
                    .onErrorReturn { RosterResult.GiveRaiseResult.Failure(it) }
                    .startWithItem(RosterResult.GiveRaiseResult.InFlight)
            }
        }

    private val sortProcessor: Transformer
        get() = Transformer { actions ->
            actions.flatMap { action ->
                useCase.getEmployeeData()
                    .zipWith(Observable.just(action).cast(RosterAction.SortAction::class.java))
                    .map { RosterResult.SortResult.Success(it.first, it.second.type) }
                    .cast(RosterResult::class.java)
                    .onErrorReturn { RosterResult.SortResult.Failure(it) }
                    .startWithItem(RosterResult.SortResult.InFlight)
            }
        }
}
