package com.pantkowski.features.base.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val disposableBag: CompositeDisposable =
        CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        this.disposableBag.dispose()
    }
}
