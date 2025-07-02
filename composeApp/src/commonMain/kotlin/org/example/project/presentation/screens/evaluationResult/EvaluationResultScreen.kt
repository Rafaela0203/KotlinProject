package org.example.project.presentation.screens.evaluationResult

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.project.LightColorScheme
import org.example.project.NavigationRoutes
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import org.example.project.presentation.shared.SharedEvaluationViewModel
import org.koin.compose.koinInject

@Composable
fun EvaluationResultScreen(
    navController: NavController,
    score: Float?,
    viewModel: EvaluationResultViewModel = koinViewModel(),
    sharedViewModel: SharedEvaluationViewModel = koinInject()
) {
    EvaluationResultContent(navController, score, viewModel, sharedViewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvaluationResultContent(
    navController: NavController,
    score: Float?,
    viewModel: EvaluationResultViewModel,
    sharedViewModel: SharedEvaluationViewModel
) {
    val scrollState = rememberScrollState()
    val uiState by viewModel.uiState.collectAsState()
    val sampleIndex by sharedViewModel.currentSampleIndex.collectAsState()

    LaunchedEffect(key1 = Unit) {
        println("LaunchedEffect na tela de resultados foi ativado.")
        val evaluationData = sharedViewModel.currentEvaluation
        println("Dados recuperados do sharedViewModel: $evaluationData")
        if (score != null && evaluationData != null) {
            viewModel.loadEvaluationResult(score, evaluationData)
        } else {
            viewModel.loadEvaluationResult(
                0f,
                null
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Avaliações",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.headlineMedium,
                        color = LightColorScheme.onPrimary,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = LightColorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LightColorScheme.primary,
                    titleContentColor = LightColorScheme.onPrimary
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Escore Qe-VESS da amostra ${sampleIndex}:",
                    style = MaterialTheme.typography.headlineSmall,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )

                Text(
                    text = uiState.sampleScore,
                    style = MaterialTheme.typography.displayMedium,
                    color = LightColorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(LightColorScheme.primary, RoundedCornerShape(8.dp))
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                )


                Text(
                    text = "Ball et al. (2017)",
                    style = MaterialTheme.typography.bodySmall,
                    color = LightColorScheme.onSurfaceVariant,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally) // Centraliza
                )

                Spacer(modifier = Modifier.height(24.dp))


                Text(
                    text = "Decisão de manejo:",
                    style = MaterialTheme.typography.titleMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                OutlinedTextField(
                    value = uiState.managementDecision,
                    onValueChange = {  },
                    readOnly = true,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = LightColorScheme.onBackground),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Resumo da avaliação:",
                    style = MaterialTheme.typography.titleMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                OutlinedTextField(
                    value = uiState.evaluationSummary,
                    onValueChange = {  },
                    readOnly = true,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = LightColorScheme.onBackground),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Outras informações importantes:",
                    style = MaterialTheme.typography.titleMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                OutlinedTextField(
                    value = uiState.otherInfoText,
                    onValueChange = {  },
                    readOnly = true,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = LightColorScheme.onBackground),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            uiState.evaluationData?.let {
                                sharedViewModel.addEvaluation(it)
                                sharedViewModel.endEvaluationSession()
                                println("FINALIZAR clicado! Sessão finalizada.")
                                navController.navigate(NavigationRoutes.FinalEvaluationSummary)
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .padding(horizontal = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LightColorScheme.secondary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "FINALIZAR",
                            color = LightColorScheme.onSecondary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        onClick = {
                            uiState.evaluationData?.let {
                                sharedViewModel.addEvaluation(it)
                                sharedViewModel.incrementSampleIndex()
                                println("PRÓXIMA AMOSTRA clicado! Amostra adicionada e voltando para a avaliação.")
                                navController.navigate(NavigationRoutes.Evaluate) {
                                    popUpTo(NavigationRoutes.Evaluate) { inclusive = true }
                                }
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .padding(horizontal = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LightColorScheme.primary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "PRÓXIMA AMOSTRA",
                            color = LightColorScheme.onPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    )
}