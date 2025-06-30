package org.example.project.di

import org.example.project.data.repository.ConfigurationRepository
import org.example.project.data.repository.ConfigurationRepositoryImpl
import org.koin.dsl.module

val iosPlatformModule = module {
    single<ConfigurationRepository> { ConfigurationRepositoryImpl() }
}