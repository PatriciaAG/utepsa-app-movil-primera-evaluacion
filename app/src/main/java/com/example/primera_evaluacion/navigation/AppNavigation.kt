package com.example.primera_evaluacion.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.primera_evaluacion.presentation.camera.CameraScreen
import com.example.primera_evaluacion.presentation.device.DeviceScreen
import com.example.primera_evaluacion.presentation.gps.GpsScreen
import com.example.primera_evaluacion.presentation.history.HistoryScreen
import com.example.primera_evaluacion.presentation.home.HomeScreen
import com.example.primera_evaluacion.presentation.sensors.SensorScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.HOME
    ) {

        composable(AppRoutes.HOME) {
            HomeScreen(
                onDeviceClick = {
                    navController.navigate(AppRoutes.DEVICE)
                },
                onSensorsClick = {
                    navController.navigate(AppRoutes.SENSORS)
                },
                onGpsClick = {
                    navController.navigate(AppRoutes.GPS)
                },
                onCameraClick = {
                    navController.navigate(AppRoutes.CAMERA)
                },
                onHistoryClick = {
                    navController.navigate(AppRoutes.HISTORY)
                }
            )
        }

        composable(AppRoutes.DEVICE) {
            DeviceScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppRoutes.SENSORS) {
            SensorScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppRoutes.GPS) {
            GpsScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppRoutes.CAMERA) {
            CameraScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppRoutes.HISTORY) {
            HistoryScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}