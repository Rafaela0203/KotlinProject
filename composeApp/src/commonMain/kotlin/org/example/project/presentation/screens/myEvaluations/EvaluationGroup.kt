package org.example.project.presentation.screens.myEvaluations

import EvaluationData

data class EvaluationGroup(
    val sessionId: Long,
    val sessionDate: String,
    val samples: List<EvaluationData>
)