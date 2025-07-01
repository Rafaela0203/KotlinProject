package org.example.project.di

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE evaluations ADD COLUMN sessionId INTEGER NOT NULL DEFAULT 0")
    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AppDatabase::class.java,
            name = "vess_app.db"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    single {
        get<AppDatabase>().evaluationDao()
    }
}