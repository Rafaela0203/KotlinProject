package org.example.project.presentation.screens.vessScores

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.example.project.LightColorScheme
import org.example.project.NavigationRoutes
import org.koin.compose.viewmodel.koinViewModel

// Assume `WebImage` is available from your core-shared-ui module as in Netflix-CMP
// import com.codandotv.streamplayerapp.core_shared_ui.widget.WebImage


// Data class to hold content for each Qe-VESS score
data class VessScoreContentData(
    val title: String,
    val description: String,
    val imageUrl: String? = null, // Optional image URL or resource path
    val specialInstruction: String? = null // For "Teste de mão" or "Avaliações extras"
)

// Define the content for each score based on your screenshots and PDF
val vessScoresData = listOf(
    VessScoreContentData(
        title = "Qualidade estrutural (Qe) 1: Friável",
        description = "Agregados quebram facilmente com os dedos. Alta porosidade. Raízes por todo o solo. Agregados são pequenos, a maioria < 6 mm após a fragmentação da amostra.",
        imageUrl = "https://example.com/image_qe1.jpg" // Placeholder URL
    ),
    VessScoreContentData(
        title = "Qe 2: Intacto",
        description = "Agregados quebram facilmente com uma mão. Sem presença de torrões (agregados grandes, duros, coesos e arredondados). A maioria dos agregados são porosos, com tamanho entre 2 mm - 7 cm. Raízes por todo o solo. Agregados quando obtidos são redondos, muito frágeis, despedaçam muito facilmente e são altamente porosos.",
        imageUrl = "https://example.com/image_qe2.jpg" // Placeholder URL
    ),
    VessScoreContentData(
        title = "Qe 3: Firme",
        description = "Maioria dos agregados quebram com uma mão. Macroporos e fissuras presentes. Porosidade e raízes: ambas dentro dos agregados. Uma mistura de agregados porosos entre 2 mm -10 cm; menos de 30% são menores que 1 cm. Alguns torrões angulares não porosos podem estar presentes.",
        imageUrl = "https://example.com/image_qe3.jpg" // Placeholder URL
    ),
    VessScoreContentData(
        title = "Qe 4: Compacto",
        description = "Quebrar agregados com uma mão requer esforço considerável. Agregados com poucos macroporos e fissuras. Raízes agrupadas em macroporos e ao redor dos agregados. A maioria dos agregados são maiores que 10 cm e sub-angulares e menos que 30% são menores que 7 cm.",
        imageUrl = "https://example.com/image_qe4.jpg", // Placeholder URL
        specialInstruction = "Teste de mão: Selecione na amostra, agregados com dimensões de 7-10 cm e utilizando somente uma mão, aplique força gradualmente com a palma da mão em vez de utilizar a ponta dos dedos."
    ),
    VessScoreContentData(
        title = "Qe 5: Muito compacto",
        description = "Difícil quebra. Porosidade muito baixa. Macroporos podem estar presentes. Pode conter zonas anaeróbicas. Poucas raízes e restritas as fissuras. A maioria dos agregados são maiores que 10 cm, poucos menores que 7 cm, com formato angular e não poroso. A cor azul-acinzentada nos agregados pode ser uma característica distintiva.",
        imageUrl = "https://example.com/image_qe5.jpg" // Placeholder URL
    ),
    VessScoreContentData(
        title = "Avaliações extras:",
        description = "Teste de mão: Em cada camada da amostra, selecione agregados com dimensões de 7-10 cm e utilizando somente uma mão, aplique força gradualmente com a palma da mão em vez de utilizar a ponta dos dedos. Caso ainda restem dúvidas sobre que nota atribuir a camada, fragmente um agregado utilizando a unha e aplicando força nos pontos de fraqueza, até obter um agregado de 1,5 - 2,0 cm. Observe sua forma e facilidade de quebra. Agregados não porosos e angulosos são indicativos de estrutura pobre e nota alta. Observação: a maior dificuldade em extrair a amostra de solo também é um indicativo de estrutura pobre e nota alta.",
        imageUrl = null // No image for this one in the screenshot
    )
)

@Composable
fun VessScoresScreen (
    navController: NavController,
    viewModel: VessScoresViewModel = koinViewModel()
){
    VessScoresContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VessScoresContent(navController: NavController) {
    val pagerState = rememberPagerState(pageCount = { vessScoresData.size })
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Atribuição da nota", // Título da TopAppBar
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
                    .padding(horizontal = 16.dp, vertical = 16.dp), // Padding lateral e vertical para o conteúdo
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween // Para empurrar botões para o fundo
            ) {
                // Conteúdo principal do pager
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), // Ocupa o máximo de espaço possível
                    verticalAlignment = Alignment.Top
                ) { page ->
                    val currentScoreData = vessScoresData[page]
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()) // Scroll interno para o conteúdo de cada página
                    ) {
                        // Título do escore (ex: Qe 1: Friável)
                        Text(
                            text = currentScoreData.title,
                            style = MaterialTheme.typography.headlineMedium,
                            color = LightColorScheme.onBackground,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start) // Alinha à esquerda
                        )

                        // Descrição do escore
                        Text(
                            text = currentScoreData.description,
                            style = MaterialTheme.typography.bodyLarge,
                            color = LightColorScheme.onBackground,
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Imagem (se existir)
                        currentScoreData.imageUrl?.let { imageUrl ->
                            Spacer(modifier = Modifier.height(16.dp))
                            // Usar WebImage se estiver disponível, caso contrário, um placeholder Box
                            // Exemplo com WebImage (requer importação de com.codandotv.streamplayerapp.core_shared_ui.widget.WebImage)
                            // WebImage(
                            //     imageUrl = imageUrl,
                            //     contentDescription = currentScoreData.title,
                            //     contentScale = ContentScale.Fit,
                            //     modifier = Modifier
                            //         .fillMaxWidth()
                            //         .height(200.dp)
                            //         .background(LightColorScheme.surface, RoundedCornerShape(8.dp))
                            // )
                            // Placeholder se WebImage não for importado ou para recursos locais
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .background(LightColorScheme.surface, RoundedCornerShape(8.dp)),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "[IMAGEM AQUI]",
                                    color = LightColorScheme.onSurfaceVariant
                                )
                            }
                        }

                        // Instrução especial (se existir)
                        currentScoreData.specialInstruction?.let { instruction ->
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = instruction,
                                style = MaterialTheme.typography.bodyLarge,
                                color = LightColorScheme.onBackground,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

                // Navegação do Pager (setas e pontos)
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(24.dp)) // Espaço antes dos botões de navegação

                    // Indicadores de página (dots)
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(pagerState.pageCount) { iteration ->
                            val color = if (pagerState.currentPage == iteration) LightColorScheme.primary else LightColorScheme.onSurfaceVariant
                            Box(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .background(color, CircleShape)
                                    .size(8.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botões Voltar e Próximo
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    if (pagerState.currentPage > 0) {
                                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                    } else {
                                        navController.popBackStack() // Volta para a tela anterior se estiver na primeira página
                                    }
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
                                text = "Voltar",
                                color = LightColorScheme.onSecondary,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    if (pagerState.currentPage < pagerState.pageCount - 1) {
                                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                    } else {
                                        // Última página: navegar para a próxima etapa do processo
                                        println("Chegou ao final dos escores VESS.")
                                        navController.navigate(NavigationRoutes.ManagementDecision) // Exemplo: para a tela de Decisão de Manejo
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
                                text = "Próximo",
                                color = LightColorScheme.onPrimary,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    )
}