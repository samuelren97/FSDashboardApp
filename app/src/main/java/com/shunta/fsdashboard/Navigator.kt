package com.shunta.fsdashboard

import androidx.navigation.NavHostController

class Navigator (private val navController: NavHostController) {
    fun navigateAircraftInfo() {
        if (currentRoute != ROUTE_AIRCRAFT_INFO_SCREEN) {
            navController.popBackStack()
            navController.navigate(ROUTE_AIRCRAFT_INFO_SCREEN)
        }
    }

    fun navigateRadio() {
        if (currentRoute != ROUTE_RADIO_SCREEN) {
            navController.popBackStack()
            navController.navigate(ROUTE_RADIO_SCREEN)
        }
    }

    fun navigateSettings() {
        if (currentRoute != ROUTE_SETTINGS_SCREEN) {
            navController.popBackStack()
            navController.navigate(ROUTE_SETTINGS_SCREEN)
        }
    }

    fun navigateAutopilot() {
        if (currentRoute != ROUTE_AUTOPILOT_SCREEN) {
            navController.popBackStack()
            navController.navigate(ROUTE_AUTOPILOT_SCREEN)
        }
    }
}