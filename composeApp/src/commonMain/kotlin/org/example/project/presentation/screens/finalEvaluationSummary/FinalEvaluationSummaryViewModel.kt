package org.example.project.presentation.screens.finalEvaluationSummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.repository.EvaluationRepository
import org.example.project.presentation.shared.SharedEvaluationViewModel
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

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

    @OptIn(ExperimentalTime::class)
    private fun loadSummary() {
        viewModelScope.launch {
            val allEvaluations = sharedViewModel.evaluations
            if (allEvaluations.isEmpty()) return@launch

            val totalSamples = allEvaluations.size
            val evaluatorName = allEvaluations.first().evaluator

            val totalLengthSum = allEvaluations.sumOf { eval -> eval.layers.sumOf { it.length.toDoubleOrNull() ?: 0.0 } }
            val weightedSum = allEvaluations.sumOf { eval ->
                eval.layers.sumOf { (it.length.toDoubleOrNull() ?: 0.0) * (it.score.toDoubleOrNull() ?: 0.0) }
            }
            val averageScore = if (totalLengthSum > 0) weightedSum / totalLengthSum else 0.0

            val managementDecision = when {
                averageScore in 1.0..2.9 -> "Boa qualidade estrutural. Nenhuma mudança de manejo necessária."
                averageScore in 3.0..3.9 -> "Qualidade estrutural razoável. Melhorias a longo prazo são recomendadas."
                averageScore >= 4.0 -> "Qualidade estrutural pobre. Danos às funções do solo, intervenção direta pode ser necessária."
                else -> "Escore fora do intervalo esperado."
            }

            val startTimeMillis = sharedViewModel.currentSessionId ?: 0L
            val endTimeMillis = sharedViewModel.sessionEndTime ?: startTimeMillis
            val startInstant = Instant.fromEpochMilliseconds(startTimeMillis)
            val startDateTime = startInstant.toLocalDateTime(TimeZone.currentSystemDefault())

            val duration = (endTimeMillis - startTimeMillis).milliseconds
            val durationText = duration.toComponents { hours, minutes, seconds, _ ->
                "${hours}h ${minutes}min ${seconds}s"
            }

            _uiState.value = FinalEvaluationSummaryUiState(
                averageScore = averageScore.toString(),
                managementDecisionForLocation = managementDecision,
                totalSamples = totalSamples,
                evaluatorName = evaluatorName,
                startDate = "${startDateTime.day}/${startDateTime.month.number}/${startDateTime.year}",
                startTime = "${startDateTime.hour}:${startDateTime.minute.toString().padStart(2, '0')}",
                evaluationDuration = durationText
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