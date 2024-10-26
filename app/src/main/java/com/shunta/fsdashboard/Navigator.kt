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
}