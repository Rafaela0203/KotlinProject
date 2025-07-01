package org.example.project.presentation.screens.finalEvaluationSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.repository.EvaluationRepository
import org.example.project.presentation.shared.SharedEvaluationViewModel

data class FinalEvaluationSummaryUiState(
    val averageScore: String = "N/A",
    val managementDecisionForLocation: String = "Carregando...",
    val totalSamples: Int = 0,
    val evaluatorName: String = "N/A",
    val startDate: String = "N/A",
    val startTime: String = "N/A",
    val evaluationDuration: String = "N/A"
)

class FinalEvaluationSummaryViewModel(
    private val sharedViewModel: SharedEvaluationViewModel,
    private val evaluationRepository: EvaluationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FinalEvaluationSummaryUiState())
    val uiState: StateFlow<FinalEvaluationSummaryUiState> = _uiState.asStateFlow()

    init {
        loadSummary()
    }

    private fun loadSummary() {
        val allEvaluations = sharedViewModel.evaluations
        val totalSamples = allEvaluations.size

        if (totalSamples > 0) {
            val totalScoreSum = allEvaluations.sumOf { evaluation ->
                evaluation.layers.sumOf { layer ->
                    (layer.length.toDoubleOrNull() ?: 0.0) * (layer.score.toDoubleOrNull() ?: 0.0)
                }
            }
            val totalLengthSum = allEvaluations.sumOf { evaluation ->
                evaluation.layers.sumOf { layer ->
                    layer.length.toDoubleOrNull() ?: 0.0
                }
            }
            val averageScore = if (totalLengthSum > 0) totalScoreSum / totalLengthSum else 0.0
            val formattedAverageScore = averageScore.toString()

            val managementDecision = when {
                averageScore >= 1.0 && averageScore <= 2.9 -> "Amostras com escores Qe-VESS de 1-2,9 indicam um solo com boa qualidade estrutural..."
                averageScore >= 3.0 && averageScore <= 3.9 -> "Amostras com escores Qe-VESS de 3-3,9 indicam um solo com qualidade estrutural razoável que pode ser melhorado..."
                averageScore >= 4.0 && averageScore <= 5.0 -> "Amostras com escores Qe-VESS de 4-5 sugerem danos às funções do solo..."
                else -> "Escore fora do intervalo esperado."
            }

            // TODO: Adicionar lógica para calcular a duração e obter o avaliador
            val evaluatorName = allEvaluations.firstOrNull()?.evaluator ?: "N/A"
            val startDate = "24 setembro 2023"
            val startTime = "15 horas"
            val evaluationDuration = "1h30min"

            _uiState.value = _uiState.value.copy(
                averageScore = formattedAverageScore,
                managementDecisionForLocation = managementDecision,
                totalSamples = totalSamples,
                evaluatorName = evaluatorName,
                startDate = startDate,
                startTime = startTime,
                evaluationDuration = evaluationDuration
            )
        }
    }

    fun saveFinalEvaluation() {
        viewModelScope.launch {
            sharedViewModel.evaluations.forEach { evaluation ->
                evaluationRepository.saveEvaluation(evaluation)
            }
        }
    }
}