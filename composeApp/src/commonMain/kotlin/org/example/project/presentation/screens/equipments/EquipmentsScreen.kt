package org.example.project.presentation.screens.equipments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack // Para o ícone de voltar, geralmente usado para RTL
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.project.LightColorScheme
import org.example.project.NavigationRoutes
import org.koin.compose.viewmodel.koinViewModel

// Importações para a imagem. Se você for usar um recurso local, precisará de:
// import org.jetbrains.compose.resources.painterResource
// import streamplayerapp_kmp.composeapp.generated.resources.Res // Ou o caminho para seus recursos

@Composable
fun EquipmentsScreen (
    navController: NavController,
    viewModel: EquipmentsViewModel = koinViewModel()
){
    EquipmentsContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquipmentsContent(navController: NavController) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Equipamentos", // Título da TopAppBar
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
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack, // Usando ArrowBack padrão ou ArrowCircleLeft se preferir
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
                // Título "Equipamentos" no conteúdo
                Text(
                    text = "Equipamentos",
                    style = MaterialTheme.typography.headlineMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start) // Alinha o texto à esquerda
                )

                // Descrição do equipamento
                Text(
                    text = "Pá reta de aproximadamente 25 cm de largura e 22-25 cm de comprimento, trena ou régua de 30 cm. Opcional: folha plástica ou saco de cor clara ou bandeja ~50 x 80 cm, câmera digital.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Imagem do equipamento
                // NOTE: Você precisaria adicionar a imagem como um recurso ao seu projeto Multiplatform.
                // Por exemplo, em 'composeApp/src/commonMain/composeResources/drawable/equipment_shovel.xml'
                // ou usar uma URL com WebImage (se você tiver um serviço para isso).
                // Por enquanto, usarei um placeholder ou o Text se não houver um recurso.
                // Se você tiver o recurso, descomente e ajuste o caminho:
                // Image(
                //     painter = painterResource(Res.drawable.equipment_shovel),
                //     contentDescription = "Pá no solo",
                //     modifier = Modifier
                //         .fillMaxWidth()
                //         .height(200.dp), // Altura ajustável
                //     contentScale = ContentScale.Fit
                // )
                // Como não tenho o recurso da imagem, vou simular com um Box e texto ou com a imagem do screenshot que está nos uploads
                // Para usar a imagem do screenshot, ela precisaria ser processada ou servida de uma URL.
                // Como é um PDF e uma imagem JPG, para o código, precisaria ser um asset.
                // Vou adicionar um Box que simula a área da imagem.

                // Para simular a imagem, vamos usar um Box com uma cor de fundo ou um texto
                // Se você tiver uma URL para a imagem, use o Composable 'WebImage' que já está no projeto Netflix-CMP.
                // Exemplo: WebImage(imageUrl = "URL_DA_IMAGEM_DA_PA", contentDescription = "Pá no solo")
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp) // Altura para a imagem
                        .background(LightColorScheme.surface, RoundedCornerShape(8.dp)), // Cor de fundo para o placeholder
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "[IMAGEM DA PÁ NO SOLO AQUI]", // Placeholder para a imagem
                        color = LightColorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

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
                            println("Clicado em Próximo na tela de Equipamentos")
                            // Exemplo: navController.navigate(NavigationRoutes.WhenToSample)
                            // Ou para o próximo tutorial na sequência, como "Onde amostrar"
                            navController.navigate(NavigationRoutes.WhereToSample) // Exemplo para a próxima tela
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