package org.example.project.data.repository

import android.content.Context
import org.example.project.domain.model.ConfigurationData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ConfigurationRepositoryImpl(private val context: Context) : ConfigurationRepository {
    private val prefs = context.getSharedPreferences("vess_config_prefs", Context.MODE_PRIVATE)

    override suspend fun saveConfiguration(config: ConfigurationData) {
        withContext(Dispatchers.IO) {
            prefs.edit().apply {
                putString("name", config.name)
                putString("email", config.email)
                putString("country", config.country)
                putString("address", config.address)
                putString("cityState", config.cityState)
                putString("language", config.language)
                apply()
            }
        }
    }

    override suspend fun getConfiguration(): ConfigurationData = withContext(Dispatchers.IO) {
        ConfigurationData(
            name = prefs.getString("name", ConfigurationData.DEFAULT.name) ?: ConfigurationData.DEFAULT.name,
            email = prefs.getString("email", ConfigurationData.DEFAULT.email) ?: ConfigurationData.DEFAULT.email,
            country = prefs.getString("country", ConfigurationData.DEFAULT.country) ?: ConfigurationData.DEFAULT.country,
            address = prefs.getString("address", ConfigurationData.DEFAULT.address) ?: ConfigurationData.DEFAULT.address,
            cityState = prefs.getString("cityState", ConfigurationData.DEFAULT.cityState) ?: ConfigurationData.DEFAULT.cityState,
            language = prefs.getString("language", ConfigurationData.DEFAULT.language) ?: ConfigurationData.DEFAULT.language
        )
    }

    override suspend fun hasConfiguration(): Boolean = withContext(Dispatchers.IO) {
        prefs.all.isNotEmpty() // Uma maneira simples de verificar se há alguma preferência salva
    }
}