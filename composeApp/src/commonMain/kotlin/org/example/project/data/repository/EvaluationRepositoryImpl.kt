package org.example.project.data.repository

import EvaluationDao
import EvaluationData

class EvaluationRepositoryImpl(
    private val evaluationDao: EvaluationDao
) : EvaluationRepository {

    override suspend fun saveEvaluation(evaluation: EvaluationData) {
        evaluationDao.insert(evaluation)
    }

    override suspend fun getAllEvaluations(): List<EvaluationData> {
        return evaluationDao.getAll()
    }

    override suspend fun getEvaluationById(id: Int): EvaluationData? {
        return evaluationDao.getById(id)
    }
}