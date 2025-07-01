package org.example.project.presentation.screens.myEvaluations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.example.project.LightColorScheme
import EvaluationData
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MyEvaluationsScreen(
    navController: NavController,
    viewModel: MyEvaluationsViewModel = koinViewModel()
) {
    MyEvaluationsContent(navController, viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyEvaluationsContent(navController: NavController, viewModel: MyEvaluationsViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Minhas Avaliações",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium,
                        color = LightColorScheme.onPrimary,
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
        content =  { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator()
                } else if (uiState.evaluationGroups.isEmpty()) {
                    Text(
                        text = "Nenhuma avaliação encontrada. Realize uma avaliação primeiro!",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(uiState.evaluationGroups) { group ->
                            EvaluationGroupItem(group = group)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun EvaluationGroupItem(group: EvaluationGroup) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Avaliação de ${group.sessionDate}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = LightColorScheme.primary
            )
            Spacer(modifier = Modifier.height(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                group.samples.forEach { evaluation ->
                    EvaluationItem(evaluation) {
                        println("Clicado na amostra: ${evaluation.sampleName}")
                    }
                }
            }
        }
    }
}


@Composable
fun EvaluationItem(evaluation: EvaluationData, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = LightColorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = evaluation.sampleName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = LightColorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Avaliador: ${evaluation.evaluator}",
                style = MaterialTheme.typography.bodyMedium,
                color = LightColorScheme.onPrimaryContainer
            )

            Text(
                text = "Local: ${evaluation.location}",
                style = MaterialTheme.typography.bodyMedium,
                color = LightColorScheme.onPrimaryContainer
            )

            Text(
                text = "Camadas avaliadas: ${evaluation.layers.size}",
                style = MaterialTheme.typography.bodyMedium,
                color = LightColorScheme.onPrimaryContainer
            )
        }
    }
}