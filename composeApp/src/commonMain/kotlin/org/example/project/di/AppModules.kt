// KotlinProject/composeApp/src/commonMain/kotlin/org/example/project/di/AppModules.kt
package org.example.project.di

import org.example.project.data.repository.ConfigurationRepository
import org.example.project.presentation.screens.aboutApp.AboutAppViewModel
import org.example.project.presentation.screens.complementaryInfo.ComplementaryInfoViewModel
import org.example.project.presentation.screens.configuration.ConfigurationViewModel
import org.example.project.presentation.screens.equipments.EquipmentsViewModel
import org.example.project.presentation.screens.evaluate.EvaluateViewModel
import org.example.project.presentation.screens.evaluationResult.EvaluationResultViewModel
import org.example.project.presentation.screens.finalEvaluationSummary.FinalEvaluationSummaryViewModel
import org.example.project.presentation.screens.home.HomeViewModel
import org.example.project.presentation.screens.managementDecision.ManagementDecisionViewModel
import org.example.project.presentation.screens.sampleExtraction.SampleExtractionViewModel
import org.example.project.presentation.screens.sampleFragmentation.SampleFragmentationViewModel
import org.example.project.presentation.screens.termsAndConditions.TermsAndConditionsViewModel
import org.example.project.presentation.screens.vessScores.VessScoresViewModel
import org.example.project.presentation.screens.whatIsVESS.WhatIsVESSViewModel
import org.example.project.presentation.screens.whenToSample.WhenToSampleViewModel
import org.example.project.presentation.screens.whereToSample.WhereToSampleViewModel
import org.example.project.presentation.shared.SharedEvaluationViewModel // Importe o novo ViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        ConfigurationViewModel(get())
    }
    viewModel {
        EquipmentsViewModel()
    }
    viewModel {
        WhereToSampleViewModel()
    }
    viewModel {
        WhenToSampleViewModel()
    }
    viewModel {
        SampleExtractionViewModel()
    }
    viewModel {
        SampleFragmentationViewModel()
    }
    viewModel {
        VessScoresViewModel()
    }
    viewModel {
        ManagementDecisionViewModel()
    }
    viewModel {
        ComplementaryInfoViewModel()
    }
    viewModel {
        WhatIsVESSViewModel()
    }
    viewModel {
        AboutAppViewModel()
    }
    viewModel {
        EvaluateViewModel()
    }
    viewModel {
        FinalEvaluationSummaryViewModel(get())
    }
    viewModel {
        EvaluationResultViewModel()
    }
    viewModel {
        TermsAndConditionsViewModel()
    }

    // Adicione a definição do ViewModel Compartilhado como um singleton
    single { SharedEvaluationViewModel() }
}

val appModules = listOf<Module>(
    viewModelModule
)