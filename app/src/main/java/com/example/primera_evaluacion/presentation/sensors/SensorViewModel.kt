package com.example.primera_evaluacion.presentation.sensors

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.primera_evaluacion.data.datasource.SensorDataSource
import com.example.primera_evaluacion.domain.model.SensorInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SensorViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val dataSource =
        SensorDataSource(application)

    private val _sensors =
        MutableStateFlow<List<SensorInfo>>(emptyList())

    val sensors: StateFlow<List<SensorInfo>> = _sensors

    init {
        loadSensors()
    }

    private fun loadSensors() {

        _sensors.value = dataSource.getSensors()
    }
}