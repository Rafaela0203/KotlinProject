package org.example.project.presentation.screens.evaluate

import EvaluationData
import LayerData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.presentation.shared.SharedEvaluationViewModel

class EvaluateViewModel(
    private val sharedViewModel: SharedEvaluationViewModel
) : ViewModel() {

    // 1. Defina o estado da avaliação usando um StateFlow
    private val _uiState = MutableStateFlow(
        EvaluationData(
            // Defina o nome da amostra com base no contador, que será atualizado no init
            sampleName = "Amostra Nº ${sharedViewModel.currentSampleIndex.value}",
            location = "",
            evaluator = "",
            layers = listOf(LayerData(length = "", score = "")),
            otherImportantInfo = ""
        )
    )
    val uiState: StateFlow<EvaluationData> = _uiState.asStateFlow()

    init {
        // Colete o valor do contador de amostras e atualize o nome da amostra sempre que ele mudar
        viewModelScope.launch {
            sharedViewModel.currentSampleIndex.collect { index ->
                _uiState.value = _uiState.value.copy(sampleName = "Amostra Nº $index")
            }
        }
    }
    // 2. Crie funções para atualizar cada parte do estado
    fun updateNumLayers(num: Int) {
        val currentLayers = _uiState.value.layers.toMutableList()
        if (num > currentLayers.size) {
            // Aumenta o número de camadas, adicionando novas
            while (currentLayers.size < num) {
                currentLayers.add(LayerData(length = "", score = ""))
            }
        } else if (num < currentLayers.size) {
            // Diminui o número de camadas, removendo do final
            while (currentLayers.size > num) {
                // Use removeAt() em vez de removeLast() para garantir a compatibilidade
                currentLayers.removeAt(currentLayers.lastIndex)
            }
        }
        _uiState.value = _uiState.value.copy(layers = currentLayers)
    }

    fun updateSampleName(name: String) {
        _uiState.value = _uiState.value.copy(sampleName = name)
    }

    fun updateLocation(location: String) {
        _uiState.value = _uiState.value.copy(location = location)
    }

    fun updateEvaluator(evaluator: String) {
        _uiState.value = _uiState.value.copy(evaluator = evaluator)
    }

    fun updateLayerLength(index: Int, length: String) {
        // Atualiza o comprimento de uma camada específica
        val updatedLayers = _uiState.value.layers.toMutableList()
        if (index < updatedLayers.size) {
            updatedLayers[index] = updatedLayers[index].copy(length = length)
            _uiState.value = _uiState.value.copy(layers = updatedLayers)
        }
    }

    fun updateLayerScore(index: Int, score: String) {
        // Atualiza a nota de uma camada específica
        val updatedLayers = _uiState.value.layers.toMutableList()
        if (index < updatedLayers.size) {
            updatedLayers[index] = updatedLayers[index].copy(score = score)
            _uiState.value = _uiState.value.copy(layers = updatedLayers)
        }
    }

    fun updateOtherImportantInfo(info: String) {
        _uiState.value = _uiState.value.copy(otherImportantInfo = info)
    }

    // 3. Implemente a lógica para calcular o escore
    fun calculateAverageScore(): Double {
        val layers = _uiState.value.layers
        val totalLength = layers.sumOf { it.length.toDoubleOrNull() ?: 0.0 }
        val weightedSum = layers.sumOf { (it.length.toDoubleOrNull() ?: 0.0) * (it.score.toDoubleOrNull() ?: 0.0) }
        return if (totalLength > 0) weightedSum / totalLength else 0.0
    }
}