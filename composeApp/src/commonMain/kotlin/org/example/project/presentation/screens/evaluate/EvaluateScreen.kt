package org.example.project.presentation.screens.evaluate

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import org.example.project.LightColorScheme
import org.example.project.NavigationRoutes
import org.example.project.utils.encodeUrl
import org.koin.compose.viewmodel.koinViewModel
import LayerData
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.ui.text.intl.Locale
import coil3.compose.AsyncImage
import org.example.project.location.GpsLocation
import org.example.project.location.rememberLocationManager
import org.example.project.presentation.shared.SharedEvaluationViewModel
import org.example.project.utils.ImagePicker
import org.koin.compose.koinInject
import kotlin.math.pow
import kotlin.math.roundToInt

@Composable
fun EvaluateScreen (
    navController: NavController,
    viewModel: EvaluateViewModel = koinViewModel()
){
    EvaluateContent(navController, viewModel)
}

private fun Double.format(digits: Int): String {
    val factor = 10.0.pow(digits.toDouble())
    return ((this * factor).roundToInt() / factor).toString()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvaluateContent(navController: NavController, viewModel: EvaluateViewModel) {
    val scrollState = rememberScrollState()
    val uiState by viewModel.uiState.collectAsState()
    var isEditingName by remember { mutableStateOf(false) }
    val sharedViewModel: SharedEvaluationViewModel = koinInject()
    var showImagePicker by remember { mutableStateOf(false) }

    val handleLocationResult = { location: GpsLocation? ->
        if (location != null) {
            val lat = location.latitude.format(6)
            val lon = location.longitude.format(6)
            val formattedLocation = "Lat: $lat, Lon: $lon"
            viewModel.updateLocation(formattedLocation)
        } else {
            viewModel.updateLocation("Não foi possível obter a localização")
        }
    }

    val locationManager = rememberLocationManager(onLocationResult = handleLocationResult)

    ImagePicker(
        show = showImagePicker,
        onImageSelected = { imagePath ->
            viewModel.updateImagePath(imagePath)
            showImagePicker = false
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Avaliações",
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isEditingName) {
                        OutlinedTextField(
                            value = uiState.sampleName,
                            onValueChange = { viewModel.updateSampleName(it) },
                            label = { Text("Nome da Amostra") },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { isEditingName = false }) {
                            Text("OK")
                        }
                    } else {
                        Text(
                            text = uiState.sampleName,
                            style = MaterialTheme.typography.headlineSmall,
                            color = LightColorScheme.onBackground,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                isEditingName = true
                            }
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = {
                            navController.navigate(NavigationRoutes.VessScores)
                        }) {
                            Icon(
                                imageVector = Icons.Default.HelpOutline,
                                contentDescription = "Escores VESS",
                                tint = LightColorScheme.primary
                            )
                        }
                        Text(
                            text = "Escores VESS",
                            style = MaterialTheme.typography.bodyLarge,
                            color = LightColorScheme.onBackground
                        )
                    }
                }
                Text(
                    text = "Quantas camadas de solo deseja avaliar?",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightColorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 1..5) {
                        Button(
                            onClick = { viewModel.updateNumLayers(i) },
                            modifier = Modifier.size(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (uiState.layers.size == i) LightColorScheme.primary else LightColorScheme.secondary,
                                contentColor = if (uiState.layers.size == i) LightColorScheme.onPrimary else LightColorScheme.onSecondary
                            ),
                            shape = CircleShape,
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(text = "$i", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                OutlinedTextField(
                    value = uiState.location,
                    onValueChange = { viewModel.updateLocation(it) },
                    label = { Text("Local/propriedade (GPS)") },
                    placeholder = { Text("Ex: Fazenda Boa Esperança (GPS)") },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(onClick = {
                            locationManager.requestLocation()
                        }) {
                            Icon(
                                imageVector = Icons.Default.GpsFixed,
                                contentDescription = "Obter Localização GPS"
                            )
                        }
                    }
                )

                OutlinedTextField(
                    value = uiState.evaluator,
                    onValueChange = { viewModel.updateEvaluator(it) },
                    label = { Text("Avaliador") },
                    placeholder = { Text("Ex: João Silva") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                repeat(uiState.layers.size) { index ->
                    Text(
                        text = "Camada ${index + 1}:",
                        style = MaterialTheme.typography.titleMedium,
                        color = LightColorScheme.onBackground,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start)
                    )
                    OutlinedTextField(
                        value = uiState.layers.getOrElse(index) { LayerData("", "") }.length,
                        onValueChange = { viewModel.updateLayerLength(index, it) },
                        label = { Text("Comprimento camada ${index + 1} (cm)") },
                        placeholder = { Text("Ex: 15") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = uiState.layers.getOrElse(index) { LayerData("", "") }.score,
                        onValueChange = { viewModel.updateLayerScore(index, it) },
                        label = { Text("Nota camada ${index + 1} (Qe-VESS)") },
                        placeholder = { Text("Ex: 3.5") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (uiState.imagePath != null) {
                    AsyncImage(
                        model = uiState.imagePath,
                        contentDescription = "Imagem da amostra",
                        modifier = Modifier.fillMaxWidth().height(200.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Button(
                    onClick = {
                        showImagePicker = true
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LightColorScheme.secondary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CameraAlt,
                            contentDescription = "Upload de arquivo",
                            tint = LightColorScheme.onSecondary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Upload de arquivo",
                            color = LightColorScheme.onSecondary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = uiState.otherImportantInfo,
                    onValueChange = { viewModel.updateOtherImportantInfo(it) },
                    label = { Text("Outras informações importantes") },
                    placeholder = { Text("Sugestões que contribuam para a construção de um histórico de avaliação da propriedade ou do grau de dificuldade de avaliação: \n" +
                            "amostra muito úmida/ amostra muito seca\n" +
                            "foi muito difícil cavar a trincheira/extrair a amostra do perfil do solo\n" +
                            "raízes estavam achatadas/tortuosas\n" +
                            "avaliação realizada em pré-plantio/pós-colheita da cultura x, no mês x, ano x\n" +
                            "tipo de solo (argiloso/arenoso/..)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    maxLines = 5
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        val averageScore = viewModel.calculateAverageScore()

                        val evaluationData = viewModel.uiState.value

                        println("Dados da avaliação a serem salvos: $evaluationData")

                        sharedViewModel.currentEvaluation = evaluationData

                        navController.navigate("${NavigationRoutes.EvaluationResult}/${averageScore}")
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(72.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LightColorScheme.primary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "AVALIAR",
                        color = LightColorScheme.onPrimary,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    )
}