package org.example.project.di

import org.example.project.data.repository.EvaluationRepository
import org.example.project.data.repository.EvaluationRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<EvaluationRepository> { EvaluationRepositoryImpl(get()) }
}