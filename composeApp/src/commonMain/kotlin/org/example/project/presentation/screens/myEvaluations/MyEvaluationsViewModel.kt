package org.example.project.presentation.screens.myEvaluations

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import EvaluationData
import org.example.project.presentation.shared.SharedEvaluationViewModel

// Crie uma classe de dados para o estado da UI da tela de histórico
data class MyEvaluationsUiState(
    val evaluations: List<EvaluationData> = emptyList()
)

class MyEvaluationsViewModel(
    private val sharedViewModel: SharedEvaluationViewModel
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyEvaluationsUiState())
    val uiState: StateFlow<MyEvaluationsUiState> = _uiState.asStateFlow()

    init {
        // Carregue a lista de avaliações assim que o ViewModel for criado
        loadEvaluations()
    }

    private fun loadEvaluations() {
        // Obtenha a lista de avaliações do ViewModel compartilhado
        val allEvaluations = sharedViewModel.evaluations
        _uiState.value = _uiState.value.copy(evaluations = allEvaluations)
    }
}