package org.example.project.presentation.screens.termsAndConditions

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Checkbox // Import Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun TermsAndConditionsScreen (
    navController: NavController,
    viewModel: TermsAndConditionsViewModel = koinViewModel()
){
    TermsAndConditionsContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsAndConditionsContent(navController: NavController) {
    val scrollState = rememberScrollState()
    var agreedToTerms by remember { mutableStateOf(false) } // State for the agreement

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Termos e condições", // Título da TopAppBar atualizado
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
                // Título principal do conteúdo (pode ser removido se a TopAppBar for suficiente)
                Text(
                    text = "Termos e condições",
                style = MaterialTheme.typography.headlineMedium,
                color = LightColorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Introdução
                Text(
                    text = "O presente termo e condições de uso visa regular a utilização dos USUÁRIOS ao Aplicativo VESS.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Ao acessar o Aplicativo VESS, o USUÁRIO expressamente aceita e concorda com as disposições destes Termos e Condições de Uso.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // DO OBJETIVO
                Text(
                    text = "DO OBJETIVO",
                    style = MaterialTheme.typography.titleMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = "Este aplicativo é uma ferramenta gratuita de uso, desenvolvido para fornecer aos agricultores, pesquisadores e profissionais da área agrícola uma avaliação prática, acessível e de baixo custo para avaliar a qualidade da estrutura do solo.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "O aplicativo permite que os usuários concluam uma autoavaliação sobre suas práticas agrícolas a partir da qualidade estrutural do solo obtida, sugerindo melhorias nas práticas de manejo e contribuindo para melhorar a sustentabilidade em suas ações de manejo do solo.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // COMUNICAÇÕES
                Text(
                    text = "COMUNICAÇÕES",
                    style = MaterialTheme.typography.titleMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = "O aplicativo VESS disponibiliza o endereço de e-mail rachelguimaraes@utfpr.edu.br como o Canal de Atendimento para receber as comunicações do USUÁRIO.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // COMPARTILHAMENTO DE DADOS COM OS DESENVOLVEDORES
                Text(
                    text = "COMPARTILHAMENTO DE DADOS COM OS DESENVOLVEDORES",
                    style = MaterialTheme.typography.titleMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = "Os desenvolvedores do aplicativo têm como princípio da atuação o respeito ao USUÁRIO, agindo sempre em conformidade com as disposições do Marco Civil da Internet (Lei Federal n. 12965/14) e com a Lei Geral de Proteção de Dados Pessoais (Lei 13.709/18).",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Ao compartilhar os resultados das avaliações com os desenvolvedores você possibilita que mais pesquisas e melhorias no aplicativo sejam realizadas.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Dados pessoais como nome, E-mail e coordenadas de localização não serão divulgados).",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "O aplicativo pode ser acessado por qualquer dispositivo móvel conectado ou não à Internet, independentemente de localização geográfica.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Em vista das diferenças que podem existir entre as legislações locais e nacionais, ao acessar o aplicativo, o USUÁRIO concorda que a legislação aplicável para fins destes Termos e Condições de Uso será aquela vigente na República Federativa do Brasil.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Botão de navegação "Voltar"
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