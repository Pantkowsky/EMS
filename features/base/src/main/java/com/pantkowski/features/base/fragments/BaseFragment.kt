package com.pantkowski.features.base.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var viewBinding: VB? = null
    val binding: VB get() = requireNotNull(viewBinding)

    protected lateinit var disposableBag: CompositeDisposable

    abstract fun setViewBindings(): VB
    abstract fun setupUiComponents(view: View, savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.disposableBag = CompositeDisposable()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.viewBinding = setViewBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupUiComponents(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.disposableBag.clear()
        this.viewBinding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        this.disposableBag.dispose()
    }
}
