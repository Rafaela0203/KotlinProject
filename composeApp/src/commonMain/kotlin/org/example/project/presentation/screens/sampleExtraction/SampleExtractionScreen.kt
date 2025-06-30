package org.example.project.presentation.screens.sampleExtraction

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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

// You might need to import painterResource if using a local drawable
// import org.jetbrains.compose.resources.painterResource
// And your generated resources if using KMP resource system
// import streamplayerapp_kmp.composeapp.generated.resources.Res
// import streamplayerapp_kmp.composeapp.generated.resources.example_image // Replace with your actual image resource name

@Composable
fun SampleExtractionScreen (
    navController: NavController,
    viewModel: SampleExtractionViewModel = koinViewModel()
){
    SampleExtractionContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleExtractionContent(navController: NavController) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Extração da amostra", // Título da TopAppBar
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.headlineMedium,
                        color = LightColorScheme.onPrimary,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) { // Botão de voltar
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
                // Título "Extração da amostra" no conteúdo
                Text(
                    text = "Extração da amostra",
                    style = MaterialTheme.typography.headlineMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start) // Alinha o texto à esquerda
                )

                // Descrição da extração da amostra
                Text(
                    text = "Abra uma pequena trincheira cavando somente em lados opostos, reservando dois lados para a retirada da amostra de solo (VER ANIMAÇÃO). Retire uma amostra de 10 a 15 cm de espessura, 20 cm de largura e aprox. 25 cm de profundidade. Acomode a amostra no chão. Meça o comprimento (profundidade) da amostra. (É possível retirar menores profundidades, mas evite amostrar profundidades maiores que 25 cm, para isso utilize o SubVESS (Ball et al., 2015)).",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp)) // Espaço antes da imagem

                // Imagem do equipamento
                // NOTE: Você precisaria adicionar a imagem como um recurso ao seu projeto Multiplatform.
                // Por exemplo, em 'composeApp/src/commonMain/composeResources/drawable/sample_extraction_image.xml'
                // ou usar uma URL com WebImage (se você tiver um serviço para isso).
                // Como não tenho o recurso da imagem, vou simular com um Box e texto.
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp) // Altura para a imagem
                        .background(LightColorScheme.surface, RoundedCornerShape(8.dp)), // Cor de fundo para o placeholder
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "[IMAGEM DA EXTRAÇÃO DA AMOSTRA AQUI]",
                        color = LightColorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(32.dp)) // Espaço antes dos botões

                // Botões de navegação "Voltar" e "Próximo"
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { navController.popBackStack() }, // Volta para a tela anterior
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .padding(horizontal = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LightColorScheme.secondary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Voltar",
                            color = LightColorScheme.onSecondary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        onClick = {
                            // Lógica para navegar para a próxima tela de tutorial/passo
                            println("Clicado em Próximo na tela Extração de Amostra")
                            navController.navigate(NavigationRoutes.SampleFragmentation) // Próxima tela lógica
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .padding(horizontal = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LightColorScheme.primary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Próximo",
                            color = LightColorScheme.onPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    )
}