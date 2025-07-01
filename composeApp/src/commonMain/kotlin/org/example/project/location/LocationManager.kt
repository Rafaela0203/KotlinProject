package org.example.project.location

import androidx.compose.runtime.Composable

data class GpsLocation(val latitude: Double, val longitude: Double)

@Composable
expect fun rememberLocationManager(
    onLocationResult: (GpsLocation?) -> Unit
): LocationManager

interface LocationManager {
    fun requestLocation()
}