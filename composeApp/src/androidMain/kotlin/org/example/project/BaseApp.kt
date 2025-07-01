package org.example.project

import android.app.Application
import org.example.project.di.androidPlatformModule
import org.example.project.di.appModules
import org.example.project.di.databaseModule    // <-- IMPORTE O NOVO MÓDULO
import org.example.project.di.repositoryModule // <-- IMPORTE O NOVO MÓDULO
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApp)
            modules(appModules + androidPlatformModule + repositoryModule + databaseModule)
        }
    }
}