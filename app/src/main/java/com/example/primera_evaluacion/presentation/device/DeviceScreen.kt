package com.example.primera_evaluacion.presentation.device

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DeviceScreen(
    onBack: () -> Unit,
    viewModel: DeviceViewModel = viewModel()
) {

    val device = remember {
        viewModel.getDeviceInfo()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text("Información del dispositivo")

        Text("Fabricante: ${device.manufacturer}")

        Text("Modelo: ${device.model}")

        Text("Android: ${device.androidVersion}")

        Text("SDK: ${device.sdkVersion}")

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.saveDeviceInfo()
            }
        ) {
            Text("Guardar en historial")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onBack
        ) {
            Text("Volver")
        }
    }
}