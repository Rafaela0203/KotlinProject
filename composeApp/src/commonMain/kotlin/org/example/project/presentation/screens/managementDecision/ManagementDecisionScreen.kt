package org.example.project.presentation.screens.managementDecision

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle // For good score icon
import androidx.compose.material.icons.filled.ThumbUp // For reasonable score icon
import androidx.compose.material.icons.filled.Warning // For poor score icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.example.project.LightColorScheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ManagementDecisionScreen (
    navController: NavController,
    viewModel: ManagementDecisionViewModel = koinViewModel()
){
    ManagementDecisionContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagementDecisionContent(navController: NavController) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Decisão de manejo",
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
                // Título "Decisão de manejo" no conteúdo
                Text(
                    text = "Decisão de manejo",
                    style = MaterialTheme.typography.headlineMedium,
                    color = LightColorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )

                Text(
                    text = "O método VESS fornece uma avaliação da qualidade estrutural atual do solo e permite decisões de manejo do solo que visam melhorar ou manter a qualidade do solo." +
                            " Para aliar a VESS à decisão de manejo do solo, são recomendadas múltiplas amostras (3 a 5), avaliadas preferencialmente por um único avaliador.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                ScoreSection(
                    title = "Escores VESS entre 1 a 2,9",
                    description = "Amostras (0-25 cm de profundidade) com escores Qe-VESS entre 1-2,9 indicam um solo com boa qualidade estrutural e não requerem mudanças no manejo.",
                    icon = Icons.Default.CheckCircle,
                    iconTint = Color(0xFF4CAF50),
                    backgroundColor = Color(0xFFE8F5E9)
                )

                ScoreSection(
                    title = "Escores VESS entre 3 a 3,9",
                    description = "Amostras (0-25 cm de profundidade) com escores Qe-VESS entre 3-3,9 indicam um solo com qualidade estrutural razoável que pode ser melhorado. Para maximizar a exploração do solo pelas raízes das culturas e para ajudar no desempenho de outras funções do solo, as mudanças no manejo devem ser a longo prazo e podem incluir a adoção de rotação de culturas com sistema radicular abundantes e/ou de penetração profunda e que maximizem a produção matéria seca, aumentando a concentração de matéria Orgânica no solo. Ademais, práticas que minimizem a compactação do solo, como a superlotação animal, em sistemas de integração lavoura-pecuária, e/ou a redução do tráfego de máquinas pesadas também contribuem para melhorar o escore da qualidade estrutural do solo.",
                    icon = Icons.Default.ThumbUp,
                    iconTint = Color(0xFFFFC107),
                    backgroundColor = Color(0xFFFFF8E1)
                )

                ScoreSection(
                    title = "Escores VESS entre 4 a 5",
                    description = "Amostras (0-25 cm de profundidade) ou camadas com escores Qe-VESS entre 4-5 sugerem, a partir de correlações com propriedades do solo (densidade, porosidade total, macroporosidade, resistência mecânica do solo à penetração das raízes, carbono orgânico, entre outros), danos às funções do solo, comprometendo sua capacidade de suporte ao crescimento, desenvolvimento e à produção das culturas. Se uma camada com escore VESS 4 estiver próximo da superfície do solo, então provavelmente será uma limitação agronômica, pois limitará o crescimento inicial das plantas, sendo geralmente necessário intervenção direta. Idealmente, recomendamos que a decisão de manejo do solo seja baseada por um conjunto de dados de qualidade do solo; características visíveis podem ser utilizadas, tais como evidências de acúmulo superficial de água, diminuição no rendimento ou evidência de estresse nas culturas, profundidade de enraizamento, relevo superficial, além de medições indiretas como densidade do solo, porosidade, resistência à penetração, macroporosidade, taxas de infiltração e por dados biológicos e de rendimento do solo. Neste caso a mudança de manejo deve ser a curto prazo, ou seja, pensando em melhorias para a próxima cultura comercial, podendo ser utilizados consórcios e culturas com sistema radicular abundante entre safras, ou o revolvimento mecânico.",
                    icon = Icons.Default.Warning,
                    iconTint = Color(0xFFF44336),
                    backgroundColor = Color(0xFFFFEBEE)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Fonte: Ball et al. (2017)",
                style = MaterialTheme.typography.bodySmall,
                color = LightColorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.End)
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    )
}

@Composable
fun ScoreSection(
    title: String,
    description: String,
    icon: ImageVector,
    iconTint: Color,
    backgroundColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = LightColorScheme.onBackground
            )
        }
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            color = LightColorScheme.onBackground
        )
    }
}