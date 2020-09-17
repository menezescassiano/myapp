package com.cassiano.myapplication

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.cassiano.myapplication.di.loadKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }

    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@BaseApplication)
            androidLogger()
            loadKoinModules()
        }
    }
}
