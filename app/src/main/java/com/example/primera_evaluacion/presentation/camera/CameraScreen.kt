package com.example.primera_evaluacion.presentation.camera

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun CameraScreen(
    onBack: () -> Unit
) {

    val context = LocalContext.current

    var photo by remember {
        mutableStateOf<Bitmap?>(null)
    }

    var message by remember {
        mutableStateOf("No hay fotografía")
    }

    val cameraLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview()
        ) { bitmap ->

            if (bitmap != null) {

                photo = bitmap

                message =
                    "Fotografía capturada correctamente"
            }
        }
    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->

            if (isGranted) {

                message =
                    "Permiso concedido. Presiona nuevamente para tomar la fotografía."

            } else {

                message =
                    "Permiso de cámara denegado"
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Cámara"
        )

        Text(
            text = message
        )

        photo?.let { bitmap ->

            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Fotografía capturada",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                val permissionStatus =
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.CAMERA
                    )

                if (
                    permissionStatus ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    cameraLauncher.launch(null)

                } else {
                    cameraPermissionLauncher.launch(
                        Manifest.permission.CAMERA
                    )
                }
            }
        ) {

            Text(
                text = "Tomar fotografía"
            )
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                photo = null

                message = "No hay fotografía"
            }
        ) {

            Text(
                text = "Limpiar fotografía"
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onBack
        ) {

            Text(
                text = "Volver"
            )
        }
    }
}