package org.example.project.presentation.screens.evaluationResult

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import EvaluationData

data class EvaluationResultUiState(
    val sampleScore: String = "",
    val managementDecision: String = "",
    val evaluationSummary: String = "",
    val otherInfoText: String = "",
    val evaluationData: EvaluationData? = null
)

class EvaluationResultViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(EvaluationResultUiState())
    val uiState: StateFlow<EvaluationResultUiState> = _uiState.asStateFlow()

    fun loadEvaluationResult(score: Float, evaluationData: EvaluationData?) {
        if (evaluationData != null) {
            val managementDecisionText = when {
                score >= 1.0f && score <= 2.9f -> {
                    "Amostras (0-25 cm de profundidade) com escores Qe-VESS entre 1-2,9 indicam um solo com boa qualidade estrutural e não requerem mudanças no manejo."
                }
                score >= 3.0f && score <= 3.9f -> {
                    "Amostras (0-25 cm de profundidade) com escores Qe-VESS entre 3-3,9 indicam um solo com qualidade estrutural razoável que pode ser melhorado. Para maximizar a exploração do solo pelas raízes das culturas e para ajudar no desempenho de outras funções do solo, as mudanças no manejo devem ser a longo prazo e podem incluir a adoção de rotação de culturas com sistema radicular abundantes e/ou de penetração profunda e que maximizem a produção matéria seca, aumentando a concentração de matéria Orgânica no solo. Ademais, práticas que minimizem a compactação do solo, como a superlotação animal, em sistemas de integração lavoura-pecuária, e/ou a redução do tráfego de máquinas pesadas também contribuem para melhorar o escore da qualidade estrutural do solo."
                }
                score >= 4.0f && score <= 5.0f -> {
                    "Amostras (0-25 cm de profundidade) ou camadas com escores Qe-VESS entre 4-5 sugerem, a partir de correlações com propriedades do solo (densidade, porosidade total, macroporosidade, resistência mecânica do solo à penetração das raízes, carbono orgânico, entre outros), danos às funções do solo, comprometendo sua capacidade de suporte ao crescimento, desenvolvimento e à produção das culturas. Se uma camada com escore VESS 4 estiver próximo da superfície do solo, então provavelmente será uma limitação agronômica, pois limitará o crescimento inicial das plantas, sendo geralmente necessário intervenção direta."
                }
                else -> "Escore fora do intervalo esperado."
            }

            val layersSummary = evaluationData.layers.joinToString("\n") { layer ->
                "Comprimento camada ${evaluationData.layers.indexOf(layer) + 1}: ${layer.length} cm; nota ${evaluationData.layers.indexOf(layer) + 1}: ${layer.score}"
            }

            _uiState.value = _uiState.value.copy(
                sampleScore = score.toString(),
                managementDecision = managementDecisionText,
                evaluationSummary = layersSummary,
                otherInfoText = evaluationData.otherImportantInfo,
                evaluationData = evaluationData
            )
        } else {
            _uiState.value = _uiState.value.copy(
                sampleScore = "Erro",
                managementDecision = "Não foi possível carregar os dados da avaliação.",
                evaluationSummary = "Dados indisponíveis.",
                otherInfoText = "Dados indisponíveis.",
                evaluationData = null
            )
        }
    }
}