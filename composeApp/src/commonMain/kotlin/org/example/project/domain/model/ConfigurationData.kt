package org.example.project.domain.model

data class ConfigurationData(
    val name: String,
    val email: String,
    val country: String,
    val address: String,
    val cityState: String,
    val language: String
) {
    companion object {
        val DEFAULT = ConfigurationData(
            name = "",
            email = "",
            country = "",
            address = "",
            cityState = "",
            language = "Português (Brasil)"
        )
    }
}