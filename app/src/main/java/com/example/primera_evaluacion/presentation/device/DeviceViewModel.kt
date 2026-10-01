package com.example.primera_evaluacion.presentation.device

import android.app.Application
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.primera_evaluacion.data.local.AppDatabase
import com.example.primera_evaluacion.data.local.HistoryEntity
import com.example.primera_evaluacion.data.repository.HistoryRepository
import com.example.primera_evaluacion.domain.model.DeviceInfo
import kotlinx.coroutines.launch

class DeviceViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository: HistoryRepository

    init {

        val database = AppDatabase.getDatabase(application)

        repository = HistoryRepository(
            database.historyDao()
        )
    }

    fun getDeviceInfo(): DeviceInfo {

        return DeviceInfo(
            manufacturer = Build.MANUFACTURER,
            model = Build.MODEL,
            androidVersion = Build.VERSION.RELEASE,
            sdkVersion = Build.VERSION.SDK_INT
        )
    }

    fun saveDeviceInfo() {

        val device = getDeviceInfo()

        viewModelScope.launch {

            repository.insert(
                HistoryEntity(
                    type = "DEVICE",
                    title = "Información del dispositivo",
                    description = "${device.manufacturer} ${device.model} - Android ${device.androidVersion}"
                )
            )
        }
    }
}