package com.example.flickstream

import android.app.Application
import com.example.flickstream.di.appModule
import com.example.flickstream.di.networkModule
import com.example.flickstream.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WatchApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WatchApplication)
            modules(listOf(appModule, networkModule, repositoryModule))
        }
    }
}