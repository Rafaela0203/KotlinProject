package org.example.project.presentation.screens.whatIsVESS

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
import org.example.project.NavigationRoutes // Mantenha esta importação, mesmo que não navegue para 'Próximo'
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WhatIsVESSScreen (
    navController: NavController,
    viewModel: WhatIsVESSViewModel = koinViewModel()
){
    WhatIsVESSContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatIsVESSContent(navController: NavController) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "O que é o VESS", // Título da TopAppBar atualizado
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
                // Título "O QUE É O VESS" no conteúdo (opcional, já está na TopAppBar)
                Text(
                    text = "O QUE É O VESS",
                    style = MaterialTheme.typography.headlineMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start) // Alinha o texto à esquerda
                )

                // Primeiro parágrafo
                Text(
                    text = "A Avaliação Visual da Estrutura do Solo (VESS) é um teste de pá em que se avalia a qualidade estrutural (Qe) do solo de forma visual e tátil. Os critérios avaliados para a atribuição de uma nota são presença de poros, tamanho, resistência e forma de agregados, presença ou não de raízes, entre outras. A nota pode variar entre Qe1 (ótimo) a Qe5 (ruim). A partir dessa nota, pode-se realizar inferências e tomar decisões em relação ao manejo do solo (Guimarães et al., 2011; Ball et al., 2017).",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                // Segundo parágrafo
                Text(
                    text = "O VESS foi desenvolvido a partir da metodologia de Peerlkamp (1959) e apresentado em sua primeira versão por Ball et al. (2007). Ajustes foram realizados por Guimarães et al. (2011), sendo esta a versão utilizada para este aplicativo. O VESS é amplamente difundido no mundo, sendo testado para uma grande variedade de solos (Franco et al., 2019).",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                // Informações adicionais com links (apenas texto por enquanto, links podem ser implementados com Browser.openUrl em KMP)
                Text(
                    text = "Mais informações podem ser encontradas em: https://www.sruc.ac.uk/vess (Inglês) ou http://paginapessoal.utfpr.edu.br/rachelguimaraes/vess (Português).",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp)) // Espaço antes do botão de voltar

                // Botão de navegação "Voltar"
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center, // Centraliza o botão
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