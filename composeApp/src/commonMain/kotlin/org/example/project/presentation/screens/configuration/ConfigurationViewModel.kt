package org.example.project.presentation.screens.configuration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.repository.ConfigurationRepository
import org.example.project.domain.model.ConfigurationData

class ConfigurationViewModel(
    private val repository: ConfigurationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConfigurationData.DEFAULT)
    val uiState: StateFlow<ConfigurationData> = _uiState.asStateFlow()

    init {
        loadConfiguration()
    }

    private fun loadConfiguration() {
        viewModelScope.launch {
            _uiState.value = repository.getConfiguration()
        }
    }

    fun updateName(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun updateCountry(country: String) {
        _uiState.value = _uiState.value.copy(country = country)
    }

    fun updateAddress(address: String) {
        _uiState.value = _uiState.value.copy(address = address)
    }

    fun updateCityState(cityState: String) {
        _uiState.value = _uiState.value.copy(cityState = cityState)
    }

    fun updateLanguage(language: String) {
        _uiState.value = _uiState.value.copy(language = language)
    }

    fun saveConfiguration(onSuccess: () -> Unit) {
        viewModelScope.launch {
            repository.saveConfiguration(_uiState.value)
            onSuccess()
        }
    }
}