package com.pantkowski.features.roster.util

import androidx.annotation.NonNull
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.internal.schedulers.ExecutorScheduler
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import java.util.concurrent.TimeUnit

class RxImmediateSchedulerRule : BeforeEachCallback, AfterEachCallback {

    private val immediateScheduler = object : Scheduler() {
        @NonNull
        override fun scheduleDirect(@NonNull run: Runnable, delay: Long, @NonNull unit: TimeUnit): Disposable {
            return super.scheduleDirect(run, 0, unit)
        }

        @NonNull
        override fun createWorker(): Worker {
            return ExecutorScheduler.ExecutorWorker({ it.run() }, true, true)
        }
    }

    override fun beforeEach(context: ExtensionContext?) {
        RxJavaPlugins.setInitIoSchedulerHandler { immediateScheduler }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediateScheduler }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediateScheduler }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediateScheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediateScheduler }
    }

    override fun afterEach(context: ExtensionContext?) {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }
}
