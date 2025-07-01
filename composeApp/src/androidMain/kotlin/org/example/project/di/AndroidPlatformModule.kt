package org.example.project.di

import AppDatabase
import androidx.room.Room
import org.example.project.data.repository.ConfigurationRepository
import org.example.project.data.repository.ConfigurationRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.example.project.data.getDatabase

val androidPlatformModule = module {
    single<ConfigurationRepository> { ConfigurationRepositoryImpl(androidContext()) }

    single {
        val builder = Room.databaseBuilder(
            context = androidContext(),
            klass = AppDatabase::class.java,
            name = "vess_database.db"
        )
        getDatabase(builder)
    }

    single {
        get<AppDatabase>().evaluationDao()
    }
}