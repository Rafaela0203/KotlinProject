package org.example.project.presentation.screens.evaluate

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt // For upload file button
import androidx.compose.material.icons.filled.HelpOutline // For help/info icon
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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
fun EvaluateScreen (
    navController: NavController,
    viewModel: EvaluateViewModel = koinViewModel()
){
    EvaluateContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvaluateContent(navController: NavController) {
    val scrollState = rememberScrollState()

    // Estados para os campos da avaliação
    var sampleName by remember { mutableStateOf("Amostra 1") } // [cite: 47]
    var numLayers by remember { mutableIntStateOf(1) } // [cite: 47] Default to 1 layer
    var location by remember { mutableStateOf("") } // [cite: 48]
    var evaluator by remember { mutableStateOf("") }
    val layerLengths = remember { mutableStateListOf<String>().apply { add("") } } // Dynamic list for lengths [cite: 48]
    val layerScores = remember { mutableStateListOf<String>().apply { add("") } } // Dynamic list for scores [cite: 48]
    var otherImportantInfo by remember { mutableStateOf("") } //

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
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack, // Usando ArrowBack padrão
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
                // Título "Avaliações" e nome da amostra
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Amostra Nº 01", //  Hardcoded as per screenshot
                        style = MaterialTheme.typography.headlineSmall,
                        color = LightColorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            // Logic to edit sample name
                            println("Editar nome da amostra")
                        }
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Help/info icon for VESS Scores
                        IconButton(onClick = {
                            // Navigate to VESS Scores tutorial
                            navController.navigate(NavigationRoutes.VessScores) //
                        }) {
                            Icon(
                                imageVector = Icons.Default.HelpOutline, //  Question mark icon
                                contentDescription = "Escores VESS",
                                tint = LightColorScheme.primary
                            )
                        }
                        Text(
                            text = "Escores VESS",
                        style = MaterialTheme.typography.bodyLarge,
                        color = LightColorScheme.onBackground
                        )
                    }
                }
                Text(
                    text = "Quantas camadas de solo deseja avaliar?",
                style = MaterialTheme.typography.bodyLarge,
                color = LightColorScheme.onBackground,
                modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )

                // Seleção do número de camadas
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Iterar de 1 a 5 para criar os botões de seleção de camadas
                    for (i in 1..5) {
                        Button(
                            onClick = {
                                numLayers = i
                                // Adjust lists for layers
                                while (layerLengths.size < numLayers) layerLengths.add("")
                                while (layerScores.size < numLayers) layerScores.add("")
                            },
                            modifier = Modifier.size(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (numLayers == i) LightColorScheme.primary else LightColorScheme.secondary.copy(alpha = 0.5f),
                                contentColor = if (numLayers == i) LightColorScheme.onPrimary else LightColorScheme.onSecondary
                            ),
                            shape = CircleShape,
                            contentPadding = PaddingValues(0.dp) // Remove padding para texto preencher
                        ) {
                            Text(text = "$i", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                // Campos de entrada para Local/Propriedade e Avaliador
                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    label = { Text("Local/propriedade (GPS)") }, //
                    placeholder = { Text("Ex: Fazenda Boa Esperança (GPS)") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = evaluator,
                    onValueChange = { evaluator = it },
                    label = { Text("Avaliador") }, //
                    placeholder = { Text("Ex: João Silva") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Campos dinâmicos para Comprimento e Nota por camada
                repeat(numLayers) { index ->
                    Text(
                        text = "Camada ${index + 1}:",
                        style = MaterialTheme.typography.titleMedium,
                        color = LightColorScheme.onBackground,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                    )
                    OutlinedTextField(
                        value = layerLengths[index],
                        onValueChange = { newValue ->
                            if (newValue.all { it.isDigit() || it == '.' }) { // Allow digits and dot for decimal values
                                layerLengths[index] = newValue
                            }
                        },
                        label = { Text("Comprimento camada ${index + 1} (cm)") }, //
                        placeholder = { Text("Ex: 15") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = layerScores[index],
                        onValueChange = { newValue ->
                            if (newValue.all { it.isDigit() || it == '.' }) { // Allow digits and dot for decimal values
                                layerScores[index] = newValue
                            }
                        },
                        label = { Text("Nota camada ${index + 1} (Qe-VESS)") }, //
                        placeholder = { Text("Ex: 3.5") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botão para upload de arquivo (imagem)
                Button(
                    onClick = {
                        // Lógica para abrir câmera/galeria para upload
                        println("Clicado em Upload de arquivo")
                        // Utilize CameraManager/GalleryManager do core-camera-gallery
                        // Ex: cameraManager.launch() ou galleryManager.launch()
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LightColorScheme.secondary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CameraAlt, // Ícone de câmera para upload [cite: 128]
                            contentDescription = "Upload de arquivo",
                            tint = LightColorScheme.onSecondary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Upload de arquivo", //
                            color = LightColorScheme.onSecondary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Outras informações importantes [cite: 49, 128, 129]
                OutlinedTextField(
                    value = otherImportantInfo,
                    onValueChange = { otherImportantInfo = it },
                    label = { Text("Outras informações importantes") }, //
                    placeholder = { Text("Ex: Amostra muito úmida, raízes achatadas...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp), // Aumente a altura para mais texto
                    maxLines = 5
                )

                // Sugestões para outras informações importantes
                Text(
                    text = "Sugestões que contribuam para a construção de um histórico de avaliação da propriedade ou do grau de dificuldade de avaliação: \n" +
                            "amostra muito úmida/ amostra muito seca\n" +
                            "foi muito difícil cavar a trincheira/extrair a amostra do perfil do solo\n" +
                            "raízes estavam achatadas/tortuosas\n" +
                            "avaliação realizada em pré-plantio/pós-colheita da cultura x, no mês x, ano x\n" +
                            "tipo de solo (argiloso/arenoso/..)",
                style = MaterialTheme.typography.bodySmall,
                color = LightColorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Botão "AVALIAR"
                Button(
                    onClick = {
                        // Lógica para processar a avaliação e navegar para a tela de resultados
                        println("Avaliar clicado!")
                        println("Nome da Amostra: $sampleName")
                        println("Número de Camadas: $numLayers")
                        println("Local: $location")
                        println("Avaliador: $evaluator")
                        layerLengths.forEachIndexed { index, length ->
                            println("Camada ${index + 1} - Comprimento: $length cm, Nota: ${layerScores[index]}")
                        }
                        println("Outras informações: $otherImportantInfo")

                        navController.navigate(NavigationRoutes.EvaluationResult) // Rota para a tela de resultado da avaliação
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(72.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LightColorScheme.primary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "AVALIAR",
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