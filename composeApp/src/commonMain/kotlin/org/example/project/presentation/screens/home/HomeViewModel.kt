package org.example.project.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.repository.ConfigurationRepository // Importe o repositório

class HomeViewModel(
    private val configurationRepository: ConfigurationRepository
) : ViewModel() {

    private val _navigateToConfiguration = MutableStateFlow(false)
    val navigateToConfiguration: StateFlow<Boolean> = _navigateToConfiguration.asStateFlow()

    fun resetNavigateToConfiguration() {
        _navigateToConfiguration.value = false
    }

    suspend fun checkConfigurationAndNavigate(onNavigateToEvaluate: () -> Unit) {
        if (configurationRepository.hasConfiguration()) {
            onNavigateToEvaluate()
        } else {
            _navigateToConfiguration.value = true
        }
    }
}