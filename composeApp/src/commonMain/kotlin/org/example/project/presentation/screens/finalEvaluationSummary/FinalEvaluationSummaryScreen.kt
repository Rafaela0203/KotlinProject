package org.example.project.presentation.screens.finalEvaluationSummary

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.project.LightColorScheme
import org.example.project.NavigationRoutes
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FinalEvaluationSummaryScreen (
    navController: NavController,
    viewModel: FinalEvaluationSummaryViewModel = koinViewModel()
){
    FinalEvaluationSummaryContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalEvaluationSummaryContent(navController: NavController) {
    val scrollState = rememberScrollState()

    // Mock data for demonstration purposes, replace with actual ViewModel data
    val averageScore = "3.1" //
    var managementDecisionForLocation by remember { mutableStateOf("A média de 3.1 indica qualidade estrutural razoável. Para maximizar a exploração do solo pelas raízes das culturas e para ajudar no desempenho de outras funções do solo, as mudanças no manejo devem ser a longo prazo e podem incluir a adoção de rotação de culturas com sistema radicular abundantes e/ou de penetração profunda.") } //
    val totalSamples = 3 //  Example
    val evaluatorName = "João Silva" // Example
    val startDate = "24 setembro 2023" //  Example
    val startTime = "15 horas" //  Example
    val endDate = "24 setembro 2023" // Example (assuming same day for simple mock)
    val endTime = "16:30" // Example
    val evaluationDuration = "1h30min" //  Example (calculate based on start/end)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Resumo da avaliação", // Título da TopAppBar
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.headlineMedium,
                        color = LightColorScheme.onPrimary,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    // Botão de voltar (opcional na tela final, pode ir direto para histórico)
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
                // Título "Escore Qe-VESS médio do local X:"
                Text(
                    text = "Escore Qe-VESS médio do local X:",
                    style = MaterialTheme.typography.headlineSmall,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start) // Alinha o texto à esquerda
                )

                // Valor do escore médio (ex: 3.1)
                Text(
                    text = averageScore,
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

                // Decisão de manejo para o local
                Text(
                    text = "Decisão de manejo para o local:",
                    style = MaterialTheme.typography.titleMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                OutlinedTextField(
                    value = managementDecisionForLocation,
                    onValueChange = { managementDecisionForLocation = it }, // Este campo é editável, conforme escopo 
                    label = { Text("Descreva o manejo para o local...") },
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = LightColorScheme.onBackground),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp), // Ajuste a altura conforme o conteúdo esperado
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Resumo do total de amostras, data/hora, tempo de avaliação
                Text(
                    text = "Resumo da avaliação:",
                    style = MaterialTheme.typography.titleMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Total de amostras: $totalSamples", 
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground
                    )
                    Text(
                        text = "Avaliador: $evaluatorName",
                        style = MaterialTheme.typography.bodyLarge,
                        color = LightColorScheme.onBackground
                    )
                    Text(
                        text = "Início da avaliação: $startDate às $startTime", 
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground
                    )
                    Text(
                        text = "Final da avaliação: $endDate às $endTime", 
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground
                    )
                    Text(
                        text = "Duração da avaliação: $evaluationDuration", 
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground
                    )
                }


                Spacer(modifier = Modifier.height(32.dp))

                // Botão "SALVAR"
                Button(
                    onClick = {
                        // Lógica para salvar a avaliação completa e navegar para o histórico
                        println("SALVAR clicado!")
                        println("Decisão de manejo para o local: $managementDecisionForLocation")
                        // Navegar para a tela de histórico [cite: 41]
                        navController.navigate(NavigationRoutes.MyEvaluations) // Supondo que esta é a rota para o histórico
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(72.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LightColorScheme.primary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "SALVAR",
                        color = LightColorScheme.onPrimary,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    )
}