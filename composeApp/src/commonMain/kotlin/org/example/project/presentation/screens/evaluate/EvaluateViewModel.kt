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
import kotlin.time.Clock
import kotlin.time.Clock.System
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class EvaluateViewModel(
    private val sharedViewModel: SharedEvaluationViewModel
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        EvaluationData(
            sampleName = "Amostra Nº ${sharedViewModel.currentSampleIndex.value}",
            location = "",
            evaluator = "",
            layers = listOf(LayerData(length = "", score = "")),
            otherImportantInfo = "",
            sessionId = sharedViewModel.currentSessionId ?: 0L,
            imagePath = null
        )
    )
    val uiState: StateFlow<EvaluationData> = _uiState.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(
            sessionId = sharedViewModel.currentSessionId ?: System.now().toEpochMilliseconds()
        )

        viewModelScope.launch {
            sharedViewModel.currentSampleIndex.collect { index ->
                _uiState.value = _uiState.value.copy(sampleName = "Amostra Nº $index")
            }
        }
    }

    fun updateImagePath(path: String?) {
        _uiState.value = _uiState.value.copy(imagePath = path)
    }

    fun updateNumLayers(num: Int) {
        val currentLayers = _uiState.value.layers.toMutableList()
        if (num > currentLayers.size) {

            while (currentLayers.size < num) {
                currentLayers.add(LayerData(length = "", score = ""))
            }
        } else if (num < currentLayers.size) {
            while (currentLayers.size > num) {
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
        val updatedLayers = _uiState.value.layers.toMutableList()
        if (index < updatedLayers.size) {
            updatedLayers[index] = updatedLayers[index].copy(length = length)
            _uiState.value = _uiState.value.copy(layers = updatedLayers)
        }
    }

    fun updateLayerScore(index: Int, score: String) {
        val updatedLayers = _uiState.value.layers.toMutableList()
        if (index < updatedLayers.size) {
            updatedLayers[index] = updatedLayers[index].copy(score = score)
            _uiState.value = _uiState.value.copy(layers = updatedLayers)
        }
    }

    fun updateOtherImportantInfo(info: String) {
        _uiState.value = _uiState.value.copy(otherImportantInfo = info)
    }

    fun calculateAverageScore(): Double {
        val layers = _uiState.value.layers
        val totalLength = layers.sumOf { it.length.toDoubleOrNull() ?: 0.0 }
        val weightedSum = layers.sumOf { (it.length.toDoubleOrNull() ?: 0.0) * (it.score.toDoubleOrNull() ?: 0.0) }
        return if (totalLength > 0) weightedSum / totalLength else 0.0
    }
}