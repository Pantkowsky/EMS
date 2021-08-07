package com.pantkowski.features.base.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

abstract class BaseActivity(@LayoutRes private val layoutRes: Int) : AppCompatActivity() {

    /**
     * List of [Module] relevant for the specific child of [BaseActivity]
     * to be used during it's lifecycle
     */
    abstract val modules: List<Module>

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        this.loadDependencies()
        super.onCreate(savedInstanceState, persistentState)
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
