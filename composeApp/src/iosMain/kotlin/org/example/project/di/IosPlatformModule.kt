package org.example.project.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.example.project.data.repository.ConfigurationRepository
import org.example.project.data.repository.ConfigurationRepositoryImpl
import org.koin.dsl.module
import platform.Foundation.NSHomeDirectory

import AppDatabase

fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFilePath = NSHomeDirectory() + "/vess_database.db"
    return Room.databaseBuilder<AppDatabase>(
        name = dbFilePath,
        factory = { AppDatabase::class.instantiateImpl() }
    )
}

fun getDatabase(builder: RoomDatabase.Builder<AppDatabase>): AppDatabase {
    return builder
        .setQueryCoroutineContext(Dispatchers.IO)
        .setDriver(BundledSQLiteDriver())
        .build()
}


val iosPlatformModule = module {
    single<ConfigurationRepository> { ConfigurationRepositoryImpl() }

    single<AppDatabase> {
        getDatabase(getDatabaseBuilder())
    }

    single {
        get<AppDatabase>().evaluationDao()
    }
}