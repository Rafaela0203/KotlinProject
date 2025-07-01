package org.example.project.presentation.screens.sampleFragmentation

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.fragmentation
import org.example.project.LightColorScheme
import org.example.project.NavigationRoutes
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SampleFragmentationScreen (
    navController: NavController,
    viewModel: SampleFragmentationViewModel = koinViewModel()
){
    SampleFragmentationContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleFragmentationContent(navController: NavController) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Fragmentação da amostra",
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
                    text = "Fragmentação da amostra",
                    style = MaterialTheme.typography.headlineMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )

                Text(
                    text = "Delicadamente manipule a amostra. Segure a amostra por baixo e abra como um livro. Observe se há camadas que se diferenciam pelo tamanho de agregados e faça a avaliação individual dessas camadas. Meça o comprimento de cada camada distinta.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(Res.drawable.fragmentation),
                    contentDescription = "Equipamentos: pá e trena",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Fit
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
                            println("Clicado em Próximo na tela Fragmentação da Amostra")
                            navController.navigate(NavigationRoutes.VessScores)
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