package org.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument // Adicione esta importação
import androidx.navigation.NavType // Adicione esta importação
import org.example.project.presentation.screens.aboutApp.AboutAppScreen
import org.example.project.presentation.screens.complementaryInfo.ComplementaryInfoScreen
import org.example.project.presentation.screens.configuration.ConfigurationScreen
import org.example.project.presentation.screens.equipments.EquipmentsScreen
import org.example.project.presentation.screens.evaluate.EvaluateScreen
import org.example.project.presentation.screens.evaluationResult.EvaluationResultScreen
import org.example.project.presentation.screens.finalEvaluationSummary.FinalEvaluationSummaryScreen

import org.example.project.presentation.screens.home.HomeScreen
import org.example.project.presentation.screens.managementDecision.ManagementDecisionScreen
import org.example.project.presentation.screens.myEvaluations.MyEvaluationsScreen
import org.example.project.presentation.screens.sampleExtraction.SampleExtractionScreen
import org.example.project.presentation.screens.sampleFragmentation.SampleFragmentationScreen
import org.example.project.presentation.screens.termsAndConditions.TermsAndConditionsScreen
import org.example.project.presentation.screens.vessScores.VessScoresScreen
import org.example.project.presentation.screens.whatIsVESS.WhatIsVESSScreen
import org.example.project.presentation.screens.whenToSample.WhenToSampleScreen
import org.example.project.presentation.screens.whereToSample.WhereToSampleScreen
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

object NavigationRoutes {
    const val Home: String = "home"
    const val Equipments: String = "equipments"
    const val WhereToSample: String = "whereToSample"
    const val WhenToSample: String = "whenToSample"
    const val SampleExtraction: String = "sampleExtraction"
    const val SampleFragmentation: String = "sampleFragmentation"
    const val VessScores: String = "vessScores"
    const val ManagementDecision: String = "managementDecision"
    const val ComplementaryInfo: String = "complementaryInfo"
    const val WhatIsVESS: String = "whatIsVESS"
    const val MyEvaluations: String = "myEvaluations"
    const val AboutApp: String = "aboutApp"
    const val Configuration: String = "configuration"
    const val Evaluate: String = "evaluate"
    const val EvaluationResult: String = "evaluationResult"
    const val FinalEvaluationSummary: String = "finalEvaluationSummary"
    const val TermsAndConditions: String = "termsAndConditions"
}

@Composable
fun App() {
    val navController = rememberNavController()

    KoinContext {
        MaterialTheme (
            colorScheme = LightColorScheme
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationRoutes.Home
            ) {
                composable(NavigationRoutes.Home) {
                    HomeScreen(navController = navController)
                }
                composable(NavigationRoutes.Evaluate) {
                    EvaluateScreen(
                        navController = navController,
                    )
                }
                composable(NavigationRoutes.Equipments) {
                    EquipmentsScreen(navController)
                }
                composable(NavigationRoutes.WhereToSample) {
                    WhereToSampleScreen(navController)
                }
                composable(NavigationRoutes.WhenToSample) {
                    WhenToSampleScreen(navController)
                }
                composable(NavigationRoutes.SampleExtraction) {
                    SampleExtractionScreen(navController)
                }
                composable(NavigationRoutes.SampleFragmentation) {
                    SampleFragmentationScreen(navController)
                }
                composable(NavigationRoutes.VessScores) {
                    VessScoresScreen(navController)
                }
                composable(NavigationRoutes.ManagementDecision) {
                    ManagementDecisionScreen(navController)
                }
                composable(NavigationRoutes.ComplementaryInfo) {
                    ComplementaryInfoScreen(navController)
                }
                composable(NavigationRoutes.WhatIsVESS) {
                    WhatIsVESSScreen(navController)
                }
                composable(NavigationRoutes.MyEvaluations) {
                    MyEvaluationsScreen(navController)
                }
                composable(NavigationRoutes.AboutApp) {
                    AboutAppScreen(navController)
                }
                composable(NavigationRoutes.Configuration) {
                    ConfigurationScreen(navController)
                }
                composable(NavigationRoutes.TermsAndConditions) {
                    TermsAndConditionsScreen(navController)
                }

                composable(
                    "${NavigationRoutes.EvaluationResult}/{score}",
                    arguments = listOf(navArgument("score") { type = NavType.FloatType })
                ) { backStackEntry ->
                    val score = backStackEntry.arguments?.getFloat("score")
                    EvaluationResultScreen(navController, score = score)
                }

                composable(NavigationRoutes.FinalEvaluationSummary) {
                    FinalEvaluationSummaryScreen(navController)
                }
            }
        }
    }
}

val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6B4423),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFC7A88B),
    onPrimaryContainer = Color.Black,
    secondary = Color(0xFFC7A88B),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFA0846C),
    onSecondaryContainer = Color.White,
    tertiary = Color(0xFF4285F4),
    onTertiary = Color.White,
    background = Color(0xFFEFE9DC),
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    error = Color(0xFFB00020),
    onError = Color.White,
    outline = Color(0xFF6B4423)
)