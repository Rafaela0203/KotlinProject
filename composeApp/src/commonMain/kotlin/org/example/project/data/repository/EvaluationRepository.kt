package org.example.project.data.repository

import EvaluationData

interface EvaluationRepository {
    suspend fun saveEvaluation(evaluation: EvaluationData)
    suspend fun getEvaluationById(id: Int): EvaluationData?
}