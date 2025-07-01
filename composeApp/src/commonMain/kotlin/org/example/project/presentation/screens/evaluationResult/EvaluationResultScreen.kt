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
import org.example.project.presentation.shared.SharedEvaluationViewModel // Importe o ViewModel compartilhado
import org.koin.compose.koinInject

@Composable
fun EvaluationResultScreen (
    navController: NavController,
    score: Float? = null,
    viewModel: EvaluationResultViewModel = koinViewModel(),
    sharedViewModel: SharedEvaluationViewModel = koinInject() // Injete o ViewModel compartilhado aqui
){
    LaunchedEffect(key1 = score) {
        if (score != null) {
            // Notifique o ViewModel com o escore e os dados completos
            viewModel.loadEvaluationResult(score, sharedViewModel.lastEvaluationData)
        }
    }

    // Corrija a chamada: passe sharedViewModel como parâmetro
    EvaluationResultContent(navController, viewModel, sharedViewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvaluationResultContent(
    navController: NavController,
    viewModel: EvaluationResultViewModel,
    sharedViewModel: SharedEvaluationViewModel // Adicione este parâmetro
) {
    val scrollState = rememberScrollState()
    val uiState by viewModel.uiState.collectAsState() // Observe o estado do ViewModel

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Avaliações", // Título da TopAppBar
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
                    .padding(paddingValues) // Aplica o padding do Scaffold
                    .padding(horizontal = 16.dp, vertical = 16.dp) // Padding lateral e vertical para o conteúdo
                    .verticalScroll(scrollState), // Adiciona scroll se o conteúdo exceder a tela
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp) // Espaçamento entre os elementos
            ) {
                // Título "Escore Qe-VESS da amostra X:"
                Text(
                    text = "Escore Qe-VESS da amostra X:",
                    style = MaterialTheme.typography.headlineSmall,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start) // Alinha o texto à esquerda
                )

                // Valor do escore, agora vindo do ViewModel
                Text(
                    text = uiState.sampleScore,
                    style = MaterialTheme.typography.displayMedium,
                    color = LightColorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(LightColorScheme.primary, RoundedCornerShape(8.dp))
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                )

                // Fonte da informação
                Text(
                    text = "Ball et al. (2017)",
                    style = MaterialTheme.typography.bodySmall,
                    color = LightColorScheme.onSurfaceVariant,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally) // Centraliza
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Decisão de manejo, vindo do ViewModel
                Text(
                    text = "Decisão de manejo:",
                    style = MaterialTheme.typography.titleMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                OutlinedTextField(
                    value = uiState.managementDecision,
                    onValueChange = { /* Não editável, apenas exibição */ },
                    readOnly = true, // Torna o campo somente leitura
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = LightColorScheme.onBackground),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp), // Ajuste a altura conforme o conteúdo esperado
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Resumo da avaliação, vindo do ViewModel
                Text(
                    text = "Resumo da avaliação:",
                    style = MaterialTheme.typography.titleMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                OutlinedTextField(
                    value = uiState.evaluationSummary,
                    onValueChange = { /* Não editável, apenas exibição */ },
                    readOnly = true, // Torna o campo somente leitura
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = LightColorScheme.onBackground),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp), // Ajuste a altura
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Outras informações importantes, vindo do ViewModel
                Text(
                    text = "Outras informações importantes:",
                    style = MaterialTheme.typography.titleMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                OutlinedTextField(
                    value = uiState.otherInfoText,
                    onValueChange = { /* Não editável, apenas exibição */ },
                    readOnly = true, // Torna o campo somente leitura
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = LightColorScheme.onBackground),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp), // Ajuste a altura
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Botões FINALIZAR e PRÓXIMA AMOSTRA
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            // Use o '?.let' para garantir que evaluationData não é nulo antes de adicionar
                            uiState.evaluationData?.let {
                                sharedViewModel.addEvaluation(it)
                                println("FINALIZAR clicado! Amostra adicionada à lista compartilhada.")
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
                            // Use o '?.let' para garantir que evaluationData não é nulo antes de adicionar
                            uiState.evaluationData?.let {
                                sharedViewModel.addEvaluation(it)
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