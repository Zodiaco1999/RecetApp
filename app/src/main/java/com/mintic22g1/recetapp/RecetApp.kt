package com.mintic22g1.recetapp

import android.app.Application
import com.mintic22g1.recetapp.di.dataSourceModule
import com.mintic22g1.recetapp.di.repoModule
import com.mintic22g1.recetapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RecetApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RecetApp)
            modules(listOf(dataSourceModule, repoModule, viewModelModule))
        }
    }
}