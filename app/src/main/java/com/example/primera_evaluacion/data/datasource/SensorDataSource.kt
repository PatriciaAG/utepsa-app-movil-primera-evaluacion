package com.example.primera_evaluacion.data.datasource

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import com.example.primera_evaluacion.domain.model.SensorInfo

class SensorDataSource(
    context: Context
) {

    private val sensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    fun getSensors(): List<SensorInfo> {

        return sensorManager
            .getSensorList(Sensor.TYPE_ALL)
            .map { sensor ->

                SensorInfo(
                    name = sensor.name,
                    type = sensor.type,
                    vendor = sensor.vendor
                )
            }
    }
}