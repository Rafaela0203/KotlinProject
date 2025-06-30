// KotlinProject/composeApp/src/iosMain/kotlin/org/example/project/data/repository/ConfigurationRepositoryImpl.kt
package org.example.project.data.repository

import org.example.project.domain.model.ConfigurationData
import platform.Foundation.NSUserDefaults
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ConfigurationRepositoryImpl : ConfigurationRepository {

    private val userDefaults = NSUserDefaults.standardUserDefaults

    private object PreferencesKeys {
        const val CONFIGURATION_DATA = "configuration_data"
    }

    override suspend fun saveConfiguration(configuration: ConfigurationData) {
        val configJson = Json.encodeToString(configuration)
        userDefaults.setObject(configJson, forKey = PreferencesKeys.CONFIGURATION_DATA)
        userDefaults.synchronize() // Força a sincronização (opcional, mas boa prática)
    }

    override suspend fun getConfiguration(): ConfigurationData {
        val configJson = userDefaults.stringForKey(PreferencesKeys.CONFIGURATION_DATA)
        return if (!configJson.isNullOrEmpty()) {
            Json.decodeFromString<ConfigurationData>(configJson)
        } else {
            // Retorna uma configuração padrão se não houver nada salvo
            ConfigurationData(
                name = "",
                email = "",
                country = "",
                address = "",
                cityState = "",
                language = "Português (Brasil)"
            )
        }
    }

    override suspend fun isConfigurationComplete(): Boolean {
        val config = getConfiguration()
        // Defina seus critérios para "configuração completa" aqui
        return config.name.isNotBlank() &&
                config.email.isNotBlank() &&
                config.country.isNotBlank() &&
                config.address.isNotBlank() &&
                config.cityState.isNotBlank()
    }
}