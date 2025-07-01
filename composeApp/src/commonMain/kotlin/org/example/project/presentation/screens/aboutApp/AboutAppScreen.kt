package org.example.project.presentation.screens.aboutApp

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
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AboutAppScreen (
    navController: NavController,
    viewModel: AboutAppViewModel = koinViewModel()
){
    AboutAppContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutAppContent(navController: NavController) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Sobre o App",
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
                    text = "SOBRE O APP",
                    style = MaterialTheme.typography.headlineMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Versão 3.0",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Esta aplicação tem por objetivo a utilização do modelo de Avaliação Visual da Estrutura do Solo (Guimarães et al., 2011).",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "Coordenadora do projeto",
                    style = MaterialTheme.typography.titleMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = "Profa. Dra. Rachel Muylaert Locks Guimarães – Universidade Tecnológica Federal do Paraná",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Desenvolvedores",
                    style = MaterialTheme.typography.titleMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = "Prof. Vinicius Pegorini – Universidade Tecnológica Federal do Paraná\n" +
                            "Rafaela Cechim dos Santos – Universidade Tecnológica Federal do Paraná\n" +
                            "Ana Paula Volpatto – Universidade Tecnológica Federal do Paraná",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Colaboradores",
                    style = MaterialTheme.typography.titleMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = "Vacilania Pacheco – Universidade Tecnológica Federal do Paraná\n" +
                            "João Victor Costa Mazzochin - Universidade Tecnológica Federal do Paraná\n" +
                            "Tatiany Keiko Mori – Universidade Tecnológica Federal do Paraná\n" +
                            "Cassio A. Tormena - Universidade Estadual de Maringá\n" +
                            "Bruce C. Ball - Scotland’s Rural College\n" +
                            "Tom Batey - University of Aberdeen\n" +
                            "Lars J. Monkholm - Aarhus University\n" +
                            "Renan Augusto Lack Barboza – Universidade Tecnológica Federal do Paraná",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Suporte financeiro",
                    style = MaterialTheme.typography.titleMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = "Projeto financiado pelo edital Universal CNPq - Processo 428579/2016-7.\n" +
                            "Fundação Araucária.\n" +
                            "Coordenação de Aperfeiçoamento de Pessoal de Nível Superior.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Fale conosco",
                    style = MaterialTheme.typography.titleMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = "Você tem dúvidas sobre o VESS?\n" +
                            "Contribua com melhorias para a metodologia ou deixe sua sugestão no e-mail abaixo:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "rachelguimaraes@utfpr.edu.br",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.primary, // Para destacar como um e-mail/link
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
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
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    )
}