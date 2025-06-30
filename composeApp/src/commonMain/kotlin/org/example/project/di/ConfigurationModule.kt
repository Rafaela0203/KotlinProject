package org.example.project.di

import org.example.project.presentation.screens.configuration.ConfigurationViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val configurationModule = module {
    viewModel { ConfigurationViewModel(get()) }
}