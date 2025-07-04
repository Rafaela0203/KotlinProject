package org.example.project.presentation.screens.myEvaluations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.repository.EvaluationRepository
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class MyEvaluationsUiState(
    val evaluationGroups: List<EvaluationGroup> = emptyList(),
    val isLoading: Boolean = true
)

class MyEvaluationsViewModel(
    private val evaluationRepository: EvaluationRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyEvaluationsUiState())
    val uiState: StateFlow<MyEvaluationsUiState> = _uiState.asStateFlow()

    init {
        loadGroupedEvaluations()
    }

    @OptIn(ExperimentalTime::class)
    private fun loadGroupedEvaluations() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val groupedMap = evaluationRepository.getAllEvaluations().groupBy { it.sessionId }

            val groups = groupedMap.map { (sessionId, samples) ->

                val instant = Instant.fromEpochMilliseconds(sessionId)
                val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

                val day = localDateTime.day.toString().padStart(2, '0')
                val month = localDateTime.month.number.toString().padStart(2, '0')
                val year = localDateTime.year
                val hour = localDateTime.hour.toString().padStart(2, '0')
                val minute = localDateTime.minute.toString().padStart(2, '0')

                val formattedDate = "$day/$month/$year, $hour:$minute"

                EvaluationGroup(
                    sessionId = sessionId,
                    sessionDate = formattedDate,
                    samples = samples
                )
            }.sortedByDescending { it.sessionId }

            _uiState.value = MyEvaluationsUiState(evaluationGroups = groups, isLoading = false)
        }
    }
}