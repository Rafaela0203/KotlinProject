package org.example.project.presentation.shared

import androidx.lifecycle.ViewModel
import EvaluationData

class SharedEvaluationViewModel : ViewModel() {
    // 1. Armazene as avaliações em uma lista mutável
    private val _evaluations = mutableListOf<EvaluationData>()
    val evaluations: List<EvaluationData> get() = _evaluations
    var lastEvaluationData: EvaluationData? = null

    // 2. Implemente a função para adicionar uma nova avaliação à lista
    fun addEvaluation(evaluation: EvaluationData) {
        _evaluations.add(evaluation)
    }

    // Opcional: Adicione uma função para limpar a lista ao iniciar uma nova sessão
    fun clearEvaluations() {
        _evaluations.clear()
    }
}