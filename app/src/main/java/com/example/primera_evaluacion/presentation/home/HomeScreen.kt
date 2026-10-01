package com.example.primera_evaluacion.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onDeviceClick: () -> Unit,
    onSensorsClick: () -> Unit,
    onGpsClick: () -> Unit,
    onCameraClick: () -> Unit,
    onHistoryClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Mi Dispositivo"
        )

        Text(
            text = "Explora las capacidades del dispositivo"
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onDeviceClick
        ) {
            Text("Información del dispositivo")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onSensorsClick
        ) {
            Text("Sensores")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onGpsClick
        ) {
            Text("GPS")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onCameraClick
        ) {
            Text("Cámara")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onHistoryClick
        ) {
            Text("Historial")
        }
    }
}