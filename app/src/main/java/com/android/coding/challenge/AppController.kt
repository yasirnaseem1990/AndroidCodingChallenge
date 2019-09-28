package com.android.coding.challenge

import android.app.Application
import android.content.Context
import com.android.coding.challenge.di.module.appModules
import com.android.coding.challenge.di.module.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppController: Application() {

    companion object {
        lateinit var ApplicationContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        ApplicationContext = this@AppController
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@AppController)
            modules(listOf(appModules, mainModule))
        }
    }
}