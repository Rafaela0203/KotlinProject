package org.example.project.data.repository

import org.example.project.domain.model.ConfigurationData

interface ConfigurationRepository {
    suspend fun saveConfiguration(config: ConfigurationData)
    suspend fun getConfiguration(): ConfigurationData
    suspend fun hasConfiguration(): Boolean
}