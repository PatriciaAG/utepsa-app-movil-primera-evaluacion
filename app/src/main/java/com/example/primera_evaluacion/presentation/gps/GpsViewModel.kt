package com.example.primera_evaluacion.presentation.gps

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.primera_evaluacion.data.local.AppDatabase
import com.example.primera_evaluacion.data.local.HistoryEntity
import com.example.primera_evaluacion.data.repository.HistoryRepository
import com.example.primera_evaluacion.domain.model.LocationInfo
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GpsViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(application)

    private val repository: HistoryRepository

    private val _location =
        MutableStateFlow<LocationInfo?>(null)

    val location: StateFlow<LocationInfo?> = _location

    private val _message =
        MutableStateFlow("Presiona el botón para obtener tu ubicación")

    val message: StateFlow<String> = _message

    init {

        val database =
            AppDatabase.getDatabase(application)

        repository =
            HistoryRepository(
                database.historyDao()
            )
    }

    fun getLocation() {

        val context = getApplication<Application>()

        val finePermission =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            )

        val coarsePermission =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )

        if (
            finePermission != PackageManager.PERMISSION_GRANTED &&
            coarsePermission != PackageManager.PERMISSION_GRANTED
        ) {

            _message.value = "Permiso de ubicación no concedido"

            return
        }

        _message.value = "Obteniendo ubicación..."

        fusedLocationClient
            .getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                null
            )
            .addOnSuccessListener { location ->

                if (location != null) {

                    val info = LocationInfo(
                        latitude = location.latitude,
                        longitude = location.longitude
                    )

                    _location.value = info

                    _message.value =
                        "Ubicación obtenida correctamente"

                    saveLocation(info)

                } else {

                    _message.value =
                        "No fue posible obtener la ubicación"
                }
            }
            .addOnFailureListener {

                _message.value =
                    "Error al obtener la ubicación"
            }
    }

    private fun saveLocation(
        location: LocationInfo
    ) {

        viewModelScope.launch {

            repository.insert(
                HistoryEntity(
                    type = "GPS",
                    title = "Ubicación obtenida",
                    description =
                        "Lat: ${location.latitude}, Lng: ${location.longitude}"
                )
            )
        }
    }
}