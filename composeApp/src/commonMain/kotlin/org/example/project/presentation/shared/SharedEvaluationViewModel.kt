package org.example.project.presentation.shared

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import EvaluationData

class SharedEvaluationViewModel : ViewModel() {
    // Uma lista para todas as avaliações concluídas na sessão
    private val _evaluations = mutableListOf<EvaluationData>()
    val evaluations: List<EvaluationData> get() = _evaluations

    // A propriedade para a avaliação atual sendo visualizada na tela de resultados
    var currentEvaluation: EvaluationData? = null
    var lastEvaluationData: EvaluationData? = null

    // Adicione um contador para o número da amostra
    private val _currentSampleIndex = MutableStateFlow(1)
    val currentSampleIndex: StateFlow<Int> = _currentSampleIndex.asStateFlow()

    // Função para adicionar uma nova avaliação à lista
    fun addEvaluation(evaluation: EvaluationData) {
        _evaluations.add(evaluation)
    }

    // Função para incrementar o contador da amostra
    fun incrementSampleIndex() {
        _currentSampleIndex.value++
    }

    // Função para reiniciar o contador ao iniciar uma nova sessão
    fun resetSampleIndex() {
        _currentSampleIndex.value = 1
        _evaluations.clear() // Também limpa a lista de avaliações
        currentEvaluation = null
    }
}