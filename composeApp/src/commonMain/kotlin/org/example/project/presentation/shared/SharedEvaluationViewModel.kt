package org.example.project.presentation.shared

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import EvaluationData
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class SharedEvaluationViewModel : ViewModel() {

    private val _evaluations = mutableListOf<EvaluationData>()
    val evaluations: List<EvaluationData> get() = _evaluations
    var currentSessionId: Long? = null

    var sessionEndTime: Long? = null

    fun startNewEvaluationSession() {
        currentSessionId = Clock.System.now().toEpochMilliseconds()
        sessionEndTime = null
        resetSampleIndex()
    }

    fun endEvaluationSession() {
        sessionEndTime = Clock.System.now().toEpochMilliseconds()
    }

    var currentEvaluation: EvaluationData? = null
    var lastEvaluationData: EvaluationData? = null

    private val _currentSampleIndex = MutableStateFlow(1)
    val currentSampleIndex: StateFlow<Int> = _currentSampleIndex.asStateFlow()

    fun addEvaluation(evaluation: EvaluationData) {
        _evaluations.add(evaluation)
    }

    fun incrementSampleIndex() {
        _currentSampleIndex.value++
    }

    fun resetSampleIndex() {
        _currentSampleIndex.value = 1
        _evaluations.clear()
        currentEvaluation = null
    }
}