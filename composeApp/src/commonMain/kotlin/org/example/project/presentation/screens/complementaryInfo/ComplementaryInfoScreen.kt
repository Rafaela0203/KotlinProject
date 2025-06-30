package org.example.project.presentation.screens.complementaryInfo

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.project.LightColorScheme
import org.example.project.presentation.screens.complementaryInfo.data.complementaryInfoData
import org.koin.compose.viewmodel.koinViewModel

// Assume `WebImage` is available from your core-shared-ui module as in Netflix-CMP
// import com.codandotv.streamplayerapp.core_shared_ui.widget.WebImage

@Composable
fun ComplementaryInfoScreen (
    navController: NavController,
    viewModel: ComplementaryInfoViewModel = koinViewModel()
){
    ComplementaryInfoContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplementaryInfoContent(navController: NavController) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Informações complementares", // Título da TopAppBar
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
                // Iterar sobre os dados para criar as seções
                complementaryInfoData.forEach { section ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(LightColorScheme.surface, RoundedCornerShape(8.dp)) // Cor de fundo para as seções
                            .padding(16.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Título da seção
                        Text(
                            text = section.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = LightColorScheme.onBackground,
                            fontWeight = FontWeight.Bold
                        )

                        // Descrição (se houver)
                        section.description?.let { desc ->
                            Text(
                                text = desc,
                                style = MaterialTheme.typography.bodyLarge,
                                color = LightColorScheme.onBackground,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        // Imagem (se houver)
                        section.imageUrl?.let { imageUrl ->
                            Spacer(modifier = Modifier.height(8.dp))
                            // Usar WebImage se estiver disponível, caso contrário, um placeholder Box
                            // Exemplo com WebImage (requer importação de com.codandotv.streamplayerapp.core_shared_ui.widget.WebImage)
                            // WebImage(
                            //     imageUrl = imageUrl,
                            //     contentDescription = section.title,
                            //     contentScale = ContentScale.Fit,
                            //     modifier = Modifier
                            //         .fillMaxWidth()
                            //         .height(200.dp)
                            // )
                            // Placeholder se WebImage não for importado ou para recursos locais
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .background(LightColorScheme.surfaceVariant.copy(alpha = 0.2f), RoundedCornerShape(8.dp)),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "[IMAGEM AQUI]",
                                    color = LightColorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp)) // Espaço entre as seções
                }

                // Importante: Nota sobre a atribuição tátil
                Text(
                    text = "*Ressaltamos que a atribuição da nota não foi realizada somente de forma visual mas tátil também.",
                    style = MaterialTheme.typography.bodySmall,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Botão de navegação "Voltar"
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center, // Centralize o botão
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { navController.popBackStack() }, // Volta para a tela anterior
                        modifier = Modifier
                            .fillMaxWidth(0.6f) // Adapta a largura
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LightColorScheme.primary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Voltar",
                            color = LightColorScheme.onPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp)) // Espaço final para o scroll
            }
        }
    )
}