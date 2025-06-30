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
fun EvaluationResultScreen (
    navController: NavController,
    viewModel: EvaluationResultViewModel = koinViewModel()
){
    EvaluationResultContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvaluationResultContent(navController: NavController) {
    val scrollState = rememberScrollState()

    // Mock data for demonstration purposes, replace with actual ViewModel data passed from EvaluateScreen
    val sampleScore = "3.1" // Example score for a single sample
    val managementDecision = "Amostras com escores Qe-VESS de 3-3,9 indicam um solo com qualidade estrutural razoável que pode ser melhorado..." //
    val evaluationSummary = "Comprimento camada 1: 4 cm; nota 1: 1\n" + //
            "Comprimento camada 2: 12 cm; nota 2: 3\n" +
            "Comprimento camada 3: 9 cm; nota 3: 4"
    val otherInfoText = "amostra muito seca; foi muito difícil cavar a trincheira/extrair a amostra do perfil do solo; raízes estavam achatadas/tortuosas; avaliação realizada em pós-colheita do milho/ jan. 2023; solo argiloso." //

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

                // Valor do escore (ex: 3.1)
                Text(
                    text = sampleScore,
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

                // Decisão de manejo
                Text(
                    text = "Decisão de manejo:",
                    style = MaterialTheme.typography.titleMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                OutlinedTextField(
                    value = managementDecision,
                    onValueChange = { /* Não editável, apenas exibição */ },
                    readOnly = true, // Torna o campo somente leitura
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = LightColorScheme.onBackground),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp), // Ajuste a altura conforme o conteúdo esperado
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Resumo da avaliação
                Text(
                    text = "Resumo da avaliação:",
                    style = MaterialTheme.typography.titleMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                OutlinedTextField(
                    value = evaluationSummary,
                    onValueChange = { /* Não editável, apenas exibição */ },
                    readOnly = true, // Torna o campo somente leitura
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = LightColorScheme.onBackground),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp), // Ajuste a altura
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Outras informações importantes
                Text(
                    text = "Outras informações importantes:",
                    style = MaterialTheme.typography.titleMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                OutlinedTextField(
                    value = otherInfoText,
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
                            // Lógica para finalizar a avaliação e navegar para a tela final de resumo (FinalEvaluationSummaryScreen)
                            println("FINALIZAR clicado!")
                            navController.navigate(NavigationRoutes.FinalEvaluationSummary) // Navega para o resumo final
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
                            // Lógica para iniciar a avaliação de uma NOVA amostra (voltar para EvaluateScreen)
                            println("PRÓXIMA AMOSTRA clicado!")
                            // Limpar estados da avaliação atual se necessário antes de navegar
                            navController.navigate(NavigationRoutes.Evaluate) {
                                popUpTo(NavigationRoutes.Evaluate) {
                                    inclusive = true // Limpa a pilha de volta até Evaluate, iniciando uma nova avaliação
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