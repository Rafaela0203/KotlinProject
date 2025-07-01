package org.example.project.presentation.screens.whereToSample

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

@Composable
fun WhereToSampleScreen (
    navController: NavController,
    viewModel: WhereToSampleViewModel = koinViewModel()
){
    WhereToSampleContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhereToSampleContent(navController: NavController) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Onde amostrar",
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
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Onde amostrar",
                    style = MaterialTheme.typography.headlineMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )

                Text(
                    text = "O VESS pode ser aplicado para qualquer solo, uso e manejo. É importante selecionar a área de interesse e sempre comparar com uma área com boa qualidade estrutural (mata nativa ou solo não cultivados). É importante comparar, por exemplo, áreas com baixa produtividade e área com alta produtividade. Dentro de uma área homogênea avalie 3 a 5 pontos (Guimarães et al., 2017).",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { navController.popBackStack() },
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
                            println("Clicado em Próximo na tela Onde Amostrar")
                            navController.navigate(NavigationRoutes.WhenToSample)
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