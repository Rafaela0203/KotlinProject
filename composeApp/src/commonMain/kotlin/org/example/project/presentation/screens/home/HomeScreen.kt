// KotlinProject/composeApp/src/commonMain/kotlin/org/example/project/presentation/screens/home/HomeScreen.kt
package org.example.project.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material.icons.outlined.HomeRepairService
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.project.LightColorScheme
import org.example.project.NavigationRoutes
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.lifecycle.viewModelScope

@Composable
fun HomeScreen (
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
){
    // Passar o ViewModel para HomeContent
    HomeContent(navController = navController, viewModel = viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(navController: NavController, viewModel: HomeViewModel) { // Receber o ViewModel aqui
    val scrollState = rememberScrollState()

    val navigateToConfiguration by viewModel.navigateToConfiguration.collectAsState()

    if (navigateToConfiguration) {
        LaunchedEffect(key1 = Unit) {
            viewModel.resetNavigateToConfiguration() // Reset o estado após a navegação
            navController.navigate(NavigationRoutes.Configuration)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "VESS",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.headlineMedium,
                        color = LightColorScheme.onPrimary,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
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
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    // Chamar a função do ViewModel para verificar e navegar
                    onClick = {
                        // Use coroutineScope para lançar a suspensão da função
                        viewModel.viewModelScope.launch {
                            viewModel.checkConfigurationAndNavigate {
                                navController.navigate(NavigationRoutes.Evaluate)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(72.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LightColorScheme.primary),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = null,
                            tint = LightColorScheme.onPrimary,
                            modifier = Modifier.size(36.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "AVALIAR",
                            color = LightColorScheme.onPrimary,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
                HorizontalDottedLineWithText(text = "Processo de avaliação") // Usar Composable com texto
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        MenuButton(
                            text = "Equipamentos",
                            icon = Icons.Outlined.HomeRepairService,
                            onClick = { navController.navigate(NavigationRoutes.Equipments) }
                        )
                        MenuButton(
                            text = "Quando amostrar",
                            icon = Icons.Outlined.Eco,
                            onClick = { navController.navigate(NavigationRoutes.WhenToSample) }
                        )
                        MenuButton(
                            text = "Fragmentação da amostra",
                            icon = Icons.Default.Info,
                            onClick = { navController.navigate(NavigationRoutes.SampleFragmentation) }
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        MenuButton(
                            text = "Onde amostrar",
                            icon = Icons.Outlined.Map,
                            onClick = { navController.navigate(NavigationRoutes.WhereToSample) }
                        )
                        MenuButton(
                            text = "Extração da amostra",
                            icon = Icons.Default.Info,
                            onClick = { navController.navigate(NavigationRoutes.SampleExtraction) }
                        )
                        MenuButton(
                            text = "Atribuição dos escores VESS",
                            icon = Icons.Default.Info,
                            onClick = { navController.navigate(NavigationRoutes.VessScores) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
                HorizontalDottedLineWithText(text = "Extras") // Usar Composable com texto
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        MenuButton(
                            text = "Decisão de manejo",
                            icon = Icons.Default.Info,
                            onClick = { navController.navigate(NavigationRoutes.ManagementDecision) }
                        )
                        MenuButton(
                            text = "O que é o VESS",
                            icon = Icons.Default.Info,
                            onClick = { navController.navigate(NavigationRoutes.WhatIsVESS) }
                        )
                        MenuButton(
                            text = "Sobre o App",
                            icon = Icons.Default.Info,
                            onClick = { navController.navigate(NavigationRoutes.AboutApp) }
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        MenuButton(
                            text = "Informações complementares",
                            icon = Icons.Default.Info,
                            onClick = { navController.navigate(NavigationRoutes.ComplementaryInfo) }
                        )
                        MenuButton(
                            text = "Minhas avaliações",
                            icon = Icons.Default.Description,
                            onClick = { navController.navigate(NavigationRoutes.MyEvaluations) }
                        )
                        MenuButton(
                            text = "Configurações",
                            icon = Icons.Default.Settings,
                            onClick = { navController.navigate(NavigationRoutes.Configuration) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center, // Centralizei o botão
                    verticalAlignment = Alignment.Top

                ) {
                    IconButton(
                        onClick = { println("Botão de Compartilhar clicado") }, // Lógica de compartilhamento
                        modifier = Modifier
                            .size(48.dp)
                            .background(LightColorScheme.primaryContainer, shape = CircleShape)

                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Share,
                            contentDescription = "Compartilhar",
                            tint = LightColorScheme.onPrimaryContainer,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        }
    )
}


@Composable
fun MenuButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(120.dp)
            .width(165.dp)
            .padding(4.dp), // Adicionado padding para consistência visual
        colors = ButtonDefaults.buttonColors(containerColor = LightColorScheme.primaryContainer),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = LightColorScheme.onPrimaryContainer,
                modifier = Modifier.size(36.dp)
            )
            Text(
                text = text,
                color = LightColorScheme.onPrimaryContainer,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold, // Adicionado de volta para consistência
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun HorizontalDottedLineWithText(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // Parte esquerda da linha pontilhada
        Spacer(
            modifier = Modifier
                .weight(1f)
                .height(2.dp)
                .drawDottedLineHorizontal(LightColorScheme.onSurface)
        )
        Text(
            text = text,
            color = LightColorScheme.onSurface,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp),
            textAlign = TextAlign.Center
        )
        // Parte direita da linha pontilhada
        Spacer(
            modifier = Modifier
                .weight(1f)
                .height(2.dp)
                .drawDottedLineHorizontal(LightColorScheme.onSurface)
        )
    }
}

fun Modifier.drawDottedLineHorizontal(color: Color) = this.drawBehind {
    val strokeWidth = 2.dp.toPx()
    val dotSize = 4.dp.toPx()
    val gap = 4.dp.toPx()
    drawLine(
        color = color,
        start = Offset(x = 0f, y = size.height / 2),
        end = Offset(x = size.width, y = size.height / 2),
        strokeWidth = strokeWidth,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(dotSize, gap), 0f)
    )
}