package org.example.project.di

import org.example.project.data.repository.ConfigurationRepository
import org.example.project.data.repository.ConfigurationRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidPlatformModule = module {
    single<ConfigurationRepository> { ConfigurationRepositoryImpl(androidContext()) }
}