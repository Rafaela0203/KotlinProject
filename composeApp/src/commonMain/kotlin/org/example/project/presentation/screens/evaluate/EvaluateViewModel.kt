package org.example.project.presentation.screens.evaluate

import EvaluationData
import LayerData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EvaluateViewModel : ViewModel() {

    // 1. Defina o estado da avaliação usando um StateFlow
    private val _uiState = MutableStateFlow(
        EvaluationData(
            sampleName = "Amostra Nº 01",
            location = "",
            evaluator = "",
            layers = listOf(LayerData(length = "", score = "")), // Inicializa com uma camada padrão
            otherImportantInfo = ""
        )
    )
    val uiState: StateFlow<EvaluationData> = _uiState.asStateFlow()

    // 2. Crie funções para atualizar cada parte do estado
    fun updateNumLayers(num: Int) {
        // Atualiza a lista de camadas para corresponder ao número selecionado
        val currentLayers = _uiState.value.layers.toMutableList()
        if (num > currentLayers.size) {
            while (currentLayers.size < num) {
                currentLayers.add(LayerData(length = "", score = ""))
            }
        } else if (num < currentLayers.size) {
            while (currentLayers.size > num) {
                currentLayers.removeLast()
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