package org.example.project
import org.koin.core.context.startKoin
import org.example.project.di.appModules

fun initKoin(){
    startKoin {
        modules(appModules)
    }
}