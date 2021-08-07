package com.pantkowski.features.base.activities

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

abstract class BaseActivity(@LayoutRes private val layoutRes: Int) : AppCompatActivity() {

    /**
     * The id of the [androidx.fragment.app.FragmentContainerView] that acts
     * as a host for any [androidx.fragment.app.Fragment] to be rendered
     * withing [BaseActivity]
     */
    @get:IdRes abstract val hostId: Int

    /**
     * List of [Module] relevant for the specific child of [BaseActivity]
     * to be used during it's lifecycle
     */
    abstract val modules: List<Module>

    override fun onCreate(savedInstanceState: Bundle?) {
        this.loadDependencies()
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
    }

    /**
     * Overridden specifically to disable the default
     * enter & exit activity animations
     */
    override fun onStart() {
        overridePendingTransition(0, 0)
        super.onStart()
    }

    override fun onDestroy() {
        unloadDependencies()
        super.onDestroy()
    }

    fun getFeatureNavigator() = findNavController(hostId)

    /**
     * On-demand [Module] loading. By default, specific modules
     * should be initialized on [AppCompatActivity.onCreate]
     */
    private fun loadDependencies() = loadKoinModules(modules)

    /**
     * On-demand [Module] unloading. By default, specific modules
     * should be unloaded on [AppCompatActivity.finish] to not hold
     * them in memory unnecessarily
     */
    private fun unloadDependencies() = unloadKoinModules(modules)
}
